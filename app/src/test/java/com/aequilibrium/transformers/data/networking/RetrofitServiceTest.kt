package com.aequilibrium.transformers.data.networking

import com.aequilibrium.transformers.BaseTest
import com.aequilibrium.transformers.data.model.Transformer
import com.google.gson.Gson
import junit.framework.Assert.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.HttpURLConnection

class RetrofitServiceTest : BaseTest(){
    lateinit var  mockWebServer : MockWebServer
    lateinit var apiService: RetrofitService
    lateinit var gson: Gson

    @Before
    fun setUp(){
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    @Test
    fun getTokenTest(){
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody("tokenResponse")
        mockWebServer.enqueue(response)

        var token = apiService.getToken().blockingGet()
        assertNotNull("Received token should not be null",token)
        assertEquals("Received token should match","tokenResponse",token)

        val errorResponse =  MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody("tokenResponse")
        mockWebServer.enqueue(errorResponse)

        try {
            token = null
            token = apiService.getToken().blockingGet()
        }catch (e : HttpException){
            assertNotNull("Should receive an http exception",e)
        }finally {
            assertNull("Received token should be null since an error occured", token)
        }
    }

    @Test
    fun createTransformerTest(){
        val transformer = Transformer().apply {
            setName("name")
            setStrength(1)
            setIntelligence(2)
            setSpeed(3)
            setEndurance(4)
            setRank(5)
            setCourage(6)
            setFirepower(7)
            setSkill(8)
            setTeam("A")
        }
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(gson.toJson(transformer))

        mockWebServer.enqueue(response)
        val receivedTransformer = apiService.createTransformer(transformer).blockingGet()
        assertNotNull("Transformer item should not be null", receivedTransformer)
        assertEquals("Transformer received should have the same name as the one sent", transformer.getName(),receivedTransformer.getName())
    }
}