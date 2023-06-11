package com.example.scopify.data.data_source.remote

import com.example.scopify.BuildConfig.API_KEY
import com.example.scopify.data.data_source.remote.api.ApiResponse
import com.example.scopify.data.data_source.remote.api.ApiService
import com.example.scopify.data.data_source.remote.response.ArticlesItem
import com.example.scopify.data.data_source.remote.response.SourcesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService){
    suspend fun getAllSources(): Flow<ApiResponse<List<SourcesItem>>> {
        return flow {
            try {
                val response = apiService.getSources(apiKey = API_KEY)
                val sources = response.sources
                if (sources.isNotEmpty()) {
                    emit(ApiResponse.Success(sources))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                if (e is HttpException) {
                    val errorCode = e.code()
                    val errorMessage = e.message()
                    emit(ApiResponse.Error("HTTP Error: $errorCode, $errorMessage"))
                } else {
                    emit(ApiResponse.Error("Network Error: ${e.message}"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSources(categoryId: String): Flow<ApiResponse<List<SourcesItem>>> {
        return flow {
            try {
                val response = apiService.getSources(categoryId, API_KEY)
                val sources = response.sources
                if (sources.isNotEmpty()) {
                    emit(ApiResponse.Success(sources))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                if (e is HttpException) {
                    val errorCode = e.code()
                    val errorMessage = e.message()
                    emit(ApiResponse.Error("HTTP Error: $errorCode, $errorMessage"))
                } else {
                    emit(ApiResponse.Error("Network Error: ${e.message}"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getArticles(sourceId: String): Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = apiService.getArticles(sourceId, API_KEY)
                val articles = response.articles
                if (articles.isNotEmpty()) {
                    emit(ApiResponse.Success(articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                if (e is HttpException) {
                    val errorCode = e.code()
                    val errorMessage = e.message()
                    emit(ApiResponse.Error("HTTP Error: $errorCode, $errorMessage"))
                } else {
                    emit(ApiResponse.Error("Network Error: ${e.message}"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchArticles(query: String, sourceId: String): Flow<ApiResponse<List<ArticlesItem?>>> {
        return flow {
            try {
                val response = apiService.searchArticles(query, sourceId, API_KEY)
                val articles = response.articles
                if (articles.isNotEmpty()) {
                    emit(ApiResponse.Success(articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                if (e is HttpException) {
                    val errorCode = e.code()
                    val errorMessage = e.message()
                    emit(ApiResponse.Error("HTTP Error: $errorCode, $errorMessage"))
                } else {
                    emit(ApiResponse.Error("Network Error: ${e.message}"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}