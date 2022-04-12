package com.example.bharatsamachar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object API {
    public const val API_key = "3dc77c2c74a14ce8bd97377d47c901de";
    private val BASE_URL = "https://newsapi.org/v2/"

    //Moshi third party Library that helps in parsing JSON objects
    // to Readable forms that can be readable by applications
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val logging = HttpLoggingInterceptor()

    //making http Client
    val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor{
                chain ->
                val builder = chain.request().newBuilder()
                builder.header("X-Api-Key", API_key)
                return@Interceptor chain.proceed(builder.build())
            }
        )
        logging.level = HttpLoggingInterceptor.Level.BODY
        addNetworkInterceptor(logging)
    }.build()

    //Initializing Retrofit Object
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(httpClient)
        .baseUrl(BASE_URL)
        .build()

    //Initializing Retrofit Service class
    val retrofitService: NewsService by lazy {
        retrofit.create(NewsService::class.java)
    }
}