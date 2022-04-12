package com.example.bharatsamachar.network

import com.example.bharatsamachar.models.TopNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


//It helps in Sending REQUEST to Server
interface NewsService {
    @GET("top-headlines")
    fun getTopArticles(
        @Query("country") country: String,
        //@Query("apikey") apikey: String
    ): retrofit2.Call<TopNewsResponse>


    @GET("top-headlines")
    fun getArticlesByCategory(
        @Query("category") category: String,
        //@Query("apikey") apikey: String
    ): retrofit2.Call<TopNewsResponse>

    @GET("everything")
    fun getArticlesBySources(@Query("sources") source: String): retrofit2.Call<TopNewsResponse>
}