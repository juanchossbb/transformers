package com.aequilibrium.transformers.createedit

import com.aequilibrium.transformers.BaseTest
import org.junit.Before
import org.junit.Test

class CreateEditViewModelTest : BaseTest(){
    lateinit var viewModel: CreateEditViewModel

    @Before
    fun setUp(){
        viewModel = CreateEditViewModel()
    }

    @Test
    fun liveDataTest(){
       /* val transformer = Transformer().apply { setName("transformer") }
        viewModel.livedata.postValue(transformer)
        val value = viewModel.livedata.value
        Assert.assertNotNull("livedata value should not be null", value)
        Assert.assertEquals("transformer name must be the same", "transformer", value?.getName())

        viewModel.errorLiveData.postValue("error")
        val errorvalue = viewModel.errorLiveData.value
        Assert.assertNotNull("livedata value should not be null", errorvalue)
        Assert.assertEquals("Livedata value should be same as posted value", "error", errorvalue)
*/
    }
}