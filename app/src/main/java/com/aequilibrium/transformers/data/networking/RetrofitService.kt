package com.aequilibrium.transformers.data.networking

import com.aequilibrium.transformers.data.model.Transformer
import com.aequilibrium.transformers.data.model.TransformerListResponse
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitService {
    @GET("allspark")
    fun getToken() : Single<String>

    @POST("transformers")
    fun createTransformer(@Body transformer: Transformer) : Single<Transformer>

    @GET("transformers")
    fun getTransformerList() : Single<TransformerListResponse>

    @DELETE("transformers/{transformerId}")
    fun deleteTransformer(@Path("transformerId") transformerId : String) : Single<Int>

    @PUT("transformers")
    fun editTransformer(@Body transformer: Transformer) : Single<Transformer>
}