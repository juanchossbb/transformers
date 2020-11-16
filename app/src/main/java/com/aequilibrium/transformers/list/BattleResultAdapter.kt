package com.aequilibrium.transformers.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aequilibrium.transformers.R
import com.aequilibrium.transformers.data.model.Transformer
import com.squareup.picasso.Picasso

class BattleResultAdapter(val list : List<Transformer>) : RecyclerView.Adapter<BattleResultAdapter.BattleResultViewHolder>(){

    class BattleResultViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val ivTeamImage = view.findViewById<ImageView>(R.id.iv_battle_result_image)
        val tvTransformerName = view.findViewById<TextView>(R.id.tv_battle_result_transformer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattleResultViewHolder {
        return BattleResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_battle_result, parent, false))
    }

    override fun onBindViewHolder(holder: BattleResultViewHolder, position: Int) {
        val transformer = list[position]
        Picasso.get().load(transformer.getTeamIcon()).fit().into(holder.ivTeamImage)
        holder.tvTransformerName.text = transformer.getName()
    }

    override fun getItemCount(): Int = list.size
}