package com.aequilibrium.transformers.data

import com.aequilibrium.transformers.TransformersApp
import com.aequilibrium.transformers.data.model.Transformer
import com.aequilibrium.transformers.data.networking.RetrofitFactory
import io.reactivex.Single

class Repository() : DataSource{
    val service = RetrofitFactory.makeRetrofitService()
    override fun retrieveToken(): Single<String> {
        return service.getToken()
    }

    override fun createTransformer(transformer: Transformer): Single<Transformer> {
        val oauthService = RetrofitFactory.makeRetrofitOAUTHService(TransformersApp.getSharedPreferences().getString("token",null))
        return oauthService.createTransformer(transformer)
    }

}