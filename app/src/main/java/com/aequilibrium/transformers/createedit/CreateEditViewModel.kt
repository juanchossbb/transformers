package com.aequilibrium.transformers.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aequilibrium.transformers.data.Repository
import com.aequilibrium.transformers.data.model.Transformer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateEditViewModel : ViewModel(){
    val repository = Repository()
    val livedata = MutableLiveData<Transformer>()
    val errorLiveData = MutableLiveData<String>()

   fun createTransformer(transformer : Transformer){
        CoroutineScope(Dispatchers.IO).launch {
            repository.createTransformer(transformer).subscribe { result, error ->
                if(result!=null) {
                    livedata.postValue(result)
                }else if(!error.message.isNullOrEmpty()){
                    errorLiveData.postValue(error.message)
                }
            }
        }
    }
}