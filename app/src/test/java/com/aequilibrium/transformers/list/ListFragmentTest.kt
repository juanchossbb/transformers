package com.aequilibrium.transformers.list

import androidx.fragment.app.testing.launchFragmentInContainer
import com.aequilibrium.transformers.BaseTest
import com.aequilibrium.transformers.data.model.Transformer
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ListFragmentTest : BaseTest(){
    lateinit var fragment: ListFragment

    @Before
    fun setUp(){
        with(launchFragmentInContainer<ListFragment>().recreate())    {
            onFragment { fragment->
                this@ListFragmentTest.fragment = fragment
            }
        }
    }

    @Test
    fun paintTransformersTest(){
        val list = mutableListOf(Transformer(), Transformer())
        fragment.paintTransformers(list)
        assertNotNull("recyclerview should not be null", fragment.rvTransformerList)
        assertNotNull("adapter should not be null", fragment.rvTransformerList.adapter)
        assertEquals("adapter should have 2 elements",2,fragment.rvTransformerList.adapter?.itemCount)
    }
}