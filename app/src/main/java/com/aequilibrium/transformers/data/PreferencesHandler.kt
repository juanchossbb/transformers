package com.aequilibrium.transformers.data

import com.aequilibrium.transformers.TransformersApp

private const val TOKEN = "token"
class PreferencesHandler {

    companion object{
        val preferences = TransformersApp.getSharedPreferences()

        fun retrieveToken() : String?{
            return preferences.getString(TOKEN,null)
        }

        fun saveToken(token : String){
            preferences.edit().putString(TOKEN, token).apply()
        }
    }
}