package com.aequilibrium.transformers.data

import com.aequilibrium.transformers.data.model.Transformer
import com.aequilibrium.transformers.data.model.TransformerListResponse
import com.aequilibrium.transformers.data.networking.RetrofitFactory
import io.reactivex.Single

class Repository() : DataSource{
    val service = RetrofitFactory.makeRetrofitService()
    override fun retrieveToken(): Single<String> {
        return service.getToken()
    }

    override fun createTransformer(transformer: Transformer): Single<Transformer> {
        val oauthService = RetrofitFactory.makeRetrofitOAUTHService(PreferencesHandler.retrieveToken())
        return oauthService.createTransformer(transformer)
    }

    override fun retrieveTransformerList(): Single<TransformerListResponse> {
        val oauthService = RetrofitFactory.makeRetrofitOAUTHService(PreferencesHandler.retrieveToken())
        return oauthService.getTransformerList()
    }

    override fun removeTransformer(transformer: Transformer): Single<Int> {
        val oauthService = RetrofitFactory.makeRetrofitOAUTHService(PreferencesHandler.retrieveToken())
        return oauthService.deleteTransformer(transformer.getId())
    }

    override fun editTransformer(transformer: Transformer): Single<Transformer> {
        val oauthService = RetrofitFactory.makeRetrofitOAUTHService(PreferencesHandler.retrieveToken())
        return oauthService.editTransformer(transformer)
    }

}