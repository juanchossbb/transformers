package com.aequilibrium.transformers.data.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val URL = "https://transformers-api.firebaseapp.com"
class RetrofitFactory {

    companion object{
        fun makeRetrofitService() : RetrofitService{
            return Retrofit.Builder()
                .baseUrl(URL)
                .client(
                    OkHttpClient.Builder()
                    .build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitService::class.java)
        }

        fun makeRetrofitOAUTHService(token : String?) : RetrofitService{
            return Retrofit.Builder()
                .baseUrl(URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor(OAuthInterceptor(token ?: "")).build())
                .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitService::class.java)
        }
    }

}

class OAuthInterceptor(private val accessToken: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "Bearer $accessToken").build()

        return chain.proceed(request)
    }
}
