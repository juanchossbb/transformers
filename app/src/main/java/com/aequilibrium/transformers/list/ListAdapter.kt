package com.aequilibrium.transformers.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aequilibrium.transformers.R
import com.aequilibrium.transformers.data.model.Transformer
import com.squareup.picasso.Picasso

class ListAdapter(private val list : MutableList<Transformer>, private val clickListener : ListFragment.TransformersClickListener) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val ivImage = view.findViewById<ImageView>(R.id.iv_item_image)
        val tvName = view.findViewById<TextView>(R.id.tv_item_name)
        val tvOverall = view.findViewById<TextView>(R.id.tv_item_overall_value)
        val btnEdit = view.findViewById<ImageButton>(R.id.btn_item_edit)
        val btnDelete = view.findViewById<ImageButton>(R.id.btn_item_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_transformer, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val transformer = list[position]
        Picasso.get().load(transformer.getTeamIcon()).fit().into(holder.ivImage)
        holder.tvName.text = transformer.getName()
        holder.tvOverall.text = transformer.getOverall().toString()
        holder.btnDelete.setOnClickListener {
            list.removeAt(position)
            clickListener.onDeleteClicked(transformer, position)
        }

        holder.btnEdit.setOnClickListener {
            clickListener.onEditClicked(transformer, position)
        }

    }

    override fun getItemCount(): Int = list.size
}