package com.aequilibrium.transformers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aequilibrium.transformers.data.PreferencesHandler
import com.aequilibrium.transformers.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(){
    val repository = Repository()
    val livedata = MutableLiveData<String>()
    val errorLiveData = MutableLiveData<String>()

    fun getToken(){
        var token = PreferencesHandler.retrieveToken()
        if (token.isNullOrEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                repository.retrieveToken().subscribe { result, error ->
                    if (result.isNotEmpty()) {
                        token = result
                        PreferencesHandler.saveToken(result)
                        livedata.postValue(result)
                    } else if (!error.message.isNullOrEmpty()) {
                        errorLiveData.postValue(error.message)
                    }
                }
            }
        }else{
            livedata.postValue(token)
        }
    }
}