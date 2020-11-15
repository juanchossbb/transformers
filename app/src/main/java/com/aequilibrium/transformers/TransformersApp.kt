package com.aequilibrium.transformers

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class TransformersApp : Application(){

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        var instance : TransformersApp? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        fun getSharedPreferences() : SharedPreferences{
            val context = applicationContext()
            return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        }
    }
}