package com.example.scopify.data.repository

import com.example.scopify.data.NetworkBoundResource
import com.example.scopify.data.Resource
import com.example.scopify.data.data_source.local.LocalDataSource
import com.example.scopify.data.data_source.remote.RemoteDataSource
import com.example.scopify.data.data_source.remote.api.ApiResponse
import com.example.scopify.data.data_source.remote.response.ArticlesItem
import com.example.scopify.data.data_source.remote.response.SourceResponse
import com.example.scopify.data.data_source.remote.response.SourcesItem
import com.example.scopify.domain.entity.Article
import com.example.scopify.domain.entity.Category
import com.example.scopify.domain.entity.Source
import com.example.scopify.domain.repository.NewsRepository
import com.example.scopify.utils.AppExecutors
import com.example.scopify.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : NewsRepository {
    override fun getCategories(): Flow<Resource<List<Category>>> {
        return object : NetworkBoundResource<List<Category>, List<Category>>(appExecutors) {
            override fun loadFromFromDb(): Flow<List<Category>> {
                return localDataSource.getCategories().map {
                    DataMapper.mapEntityToDomainCategory(it)
                }
            }

            override fun shouldFetch(data: List<Category>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<Category>>> =
                flow { emit(ApiResponse.Empty) }

            override suspend fun saveCallResult(data: List<Category>) {
                val categories = listOf(
                    Category("business", "Business"),
                    Category("entertainment", "Entertainment"),
                    Category("general", "General"),
                    Category("health", "Health"),
                    Category("science", "Science"),
                    Category("sports", "Sports"),
                    Category("technology", "Technology")
                )

                val categoryEntity = DataMapper.mapDomainToEntityCategory(categories)
                localDataSource.saveCategories(categoryEntity)
            }
        }.asFlow()
    }

    override fun getSources(categoryId: String): Flow<Resource<List<Source>>> {
        return object : NetworkBoundResource<List<Source>, List<SourcesItem>>(appExecutors) {
            override fun loadFromFromDb(): Flow<List<Source>> {
                return localDataSource.getSources().map {
                    DataMapper.mapEntityToDomainSource(it)
                }
            }

            override fun shouldFetch(data: List<Source>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SourcesItem>>> =
                remoteDataSource.getSources(categoryId)

            override suspend fun saveCallResult(data: List<SourcesItem>) {
                val sources = DataMapper.mapResponseToEntitySource(data)
                localDataSource.saveSources(sources)
            }

        }.asFlow()
    }

    override fun getArticles(sourceId: String): Flow<Resource<List<Article>>> {
        return object : NetworkBoundResource<List<Article>, List<ArticlesItem>>(appExecutors) {
            override fun loadFromFromDb(): Flow<List<Article>> {
                return localDataSource.getArticles().map {
                    DataMapper.mapEntityToDomainArticle(it)
                }
            }

            override fun shouldFetch(data: List<Article>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getArticles(sourceId)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val articles = DataMapper.mapResponseToEntityArticle(data)
                localDataSource.saveArticles(articles)
            }
        }.asFlow()
    }

    override fun searchSources(query: String): Flow<Resource<List<Source>>> {
        return object : NetworkBoundResource<List<Source>, List<SourcesItem>>(appExecutors) {
            override fun loadFromFromDb(): Flow<List<Source>> {
                return localDataSource.searchSources(query).map {
                    DataMapper.mapEntityToDomainSource(it)
                }
            }

            override fun shouldFetch(data: List<Source>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SourcesItem>>> =
                flow { emit(ApiResponse.Empty) }

            override suspend fun saveCallResult(data: List<SourcesItem>) {
                val sources = DataMapper.mapResponseToEntitySource(data)
                localDataSource.saveSources(sources)
            }

        }.asFlow()
    }

    override fun searchArticles(query: String): Flow<Resource<List<Article>>> {
        return object : NetworkBoundResource<List<Article>, List<ArticlesItem>>(appExecutors) {
            override fun loadFromFromDb(): Flow<List<Article>> {
                return localDataSource.searchArticles(query).map {
                    DataMapper.mapEntityToDomainArticle(it)
                }
            }

            override fun shouldFetch(data: List<Article>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getArticles(query)

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val articles = DataMapper.mapResponseToEntityArticle(data)
                localDataSource.saveArticles(articles)
            }
        }.asFlow()
    }
}