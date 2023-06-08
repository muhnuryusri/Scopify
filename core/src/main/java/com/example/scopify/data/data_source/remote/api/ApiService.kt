package com.example.scopify.data.data_source.remote.api

import com.example.scopify.data.data_source.remote.response.ArticleResponse
import com.example.scopify.data.data_source.remote.response.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") categoryId: String,
        @Query("apiKey") apiKey: String,
    ) : SourceResponse

    @GET("top-headlines")
    suspend fun getArticles(
        @Query("sources") sourceId: String,
        @Query("apiKey") apiKey: String,
    ) : ArticleResponse

    @GET("top-headlines")
    suspend fun searchArticles(
        @Query("q") query: String,
        @Query("sources") sourceId: String,
        @Query("apiKey") apiKey: String,
    ) : ArticleResponse
}