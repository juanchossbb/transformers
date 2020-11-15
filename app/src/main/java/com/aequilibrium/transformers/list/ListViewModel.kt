package com.aequilibrium.transformers.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aequilibrium.transformers.data.Repository
import com.aequilibrium.transformers.data.model.Transformer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel(){
    val repository = Repository()
    val livedata = MutableLiveData<MutableList<Transformer>>()
    val removeLiveData = MutableLiveData<Int>()
    val errorLiveData = MutableLiveData<String>()

    fun getTransformerList(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.retrieveTransformerList().subscribe { response, error ->
                if (response!=null){
                    livedata.postValue(response.transformers)
                }else if(!error.message.isNullOrEmpty()){
                    errorLiveData.postValue(error.message)
                }
            }
        }
    }

    fun removeTransformer(transformer: Transformer){
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeTransformer(transformer).subscribe { response, error ->
                if (response!=null){
                    removeLiveData.postValue(204)
                }else if (error != null){
                    removeLiveData.postValue(401)
                }
            }
        }
    }
}