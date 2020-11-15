package com.aequilibrium.transformers.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aequilibrium.transformers.MainActivityInterface
import com.aequilibrium.transformers.R
import com.aequilibrium.transformers.data.model.Transformer

class ListFragment : Fragment(){
    lateinit var btnFight : Button
    lateinit var rvTransformerList : RecyclerView
    lateinit var adapter: ListAdapter
    var mainInterface: MainActivityInterface? = null
    @VisibleForTesting
    val viewModel by lazy { ListViewModel() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnFight = view.findViewById(R.id.btn_transformer_fight)
        rvTransformerList = view.findViewById(R.id.rv_transformer_list)
        rvTransformerList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
        getTransformerList()

        viewModel.livedata.observe(requireActivity(), {
            paintTransformers(it)
            if(it.size>0)
            btnFight.apply {
                isEnabled = true
                setOnClickListener {view->
                    viewModel.wageWar(it)
                }
            }
        })

    }

    private fun getTransformerList(){
        mainInterface?.showProgressBar(true)
        viewModel.getTransformerList()
    }

    @VisibleForTesting
    fun paintTransformers(list : MutableList<Transformer>){
        mainInterface?.showProgressBar(false)
        adapter = ListAdapter(list, clickListener)
        rvTransformerList.adapter = adapter
    }

    private val clickListener = object : TransformersClickListener {
        override fun onDeleteClicked(transformer: Transformer, position: Int) {
            mainInterface?.showProgressBar(true)
            viewModel.removeTransformer(transformer)
            viewModel.removeLiveData.observe(requireActivity(),{
                //Throwing error ItemNotFoundError but item removing anyway
                adapter.notifyItemRemoved(position)
                mainInterface?.showProgressBar(false)
            })

        }

        override fun onEditClicked(transformer: Transformer, position: Int) {
            mainInterface?.launchEditFragment(transformer)
        }
    }

    interface  TransformersClickListener{
        fun onDeleteClicked(transformer : Transformer, position : Int)
        fun onEditClicked(transformer : Transformer, position : Int)
    }

    companion object{
        fun getInstance(mainInterface : MainActivityInterface) : ListFragment{
            return ListFragment().apply { this.mainInterface = mainInterface }
        }
    }
}