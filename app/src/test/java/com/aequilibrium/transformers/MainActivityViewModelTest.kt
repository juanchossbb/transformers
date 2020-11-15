package com.aequilibrium.transformers

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MainActivityViewModelTest : BaseTest(){
    lateinit var viewModel: MainActivityViewModel


    @Before
    fun setUp(){
        viewModel = MainActivityViewModel()
    }

    @Test
    fun liveDataTest(){
        viewModel.livedata.postValue("token value")
        val value = viewModel.livedata.value
        assertNotNull("livedata value should not be null",value)
        assertEquals("Livedata value should be same as posted value", "token value",value)

        viewModel.errorLiveData.postValue("error")
        val errorvalue = viewModel.errorLiveData.value
        assertNotNull("livedata value should not be null",errorvalue)
        assertEquals("Livedata value should be same as posted value", "error",errorvalue)

    }

}