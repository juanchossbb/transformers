package com.aequilibrium.transformers.data.networking

import com.aequilibrium.transformers.data.model.Transformer
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET("allspark")
    fun getToken() : Single<String>

    @POST("transformers")
    fun createTransformer(@Body transformer: Transformer) : Single<Transformer>
}