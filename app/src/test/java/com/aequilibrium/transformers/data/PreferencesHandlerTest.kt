package com.aequilibrium.transformers.data

import com.aequilibrium.transformers.BaseTest
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test

class PreferencesHandlerTest : BaseTest(){

    @Test
    fun tokenPreferencesTest(){
        assertNull("Token should be empty",PreferencesHandler.retrieveToken())
        PreferencesHandler.saveToken("token")
        assertEquals("Token should match saved value","token",PreferencesHandler.retrieveToken())
    }
}