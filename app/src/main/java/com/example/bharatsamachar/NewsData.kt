package com.example.bharatsamachar

data class NewsData(
    val id :Int,
    val image:Int = R.drawable.breaking_news,
    val title:String,
    val author:String,
    val description:String,
    val publishedAt:String
    )

