package com.example.scopify.data.data_source.local

import com.example.scopify.data.data_source.local.entity.ArticleEntity
import com.example.scopify.data.data_source.local.entity.CategoryEntity
import com.example.scopify.data.data_source.local.entity.SourceEntity
import com.example.scopify.data.data_source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val newsDao: NewsDao) {
    fun getCategories(): Flow<List<CategoryEntity>> = newsDao.getCategories()
    suspend fun saveCategories(categories: List<CategoryEntity>) = newsDao.saveCategories(categories)
    fun getSources(): Flow<List<SourceEntity>> = newsDao.getSources()
    suspend fun saveSources(sources: List<SourceEntity>) = newsDao.saveSources(sources)
    fun getArticles(): Flow<List<ArticleEntity>> = newsDao.getArticles()
    suspend fun saveArticles(articles: List<ArticleEntity>) = newsDao.saveArticles(articles)
    fun searchSources(query: String): Flow<List<SourceEntity>> = newsDao.searchSources(query)
    fun searchArticles(query: String): Flow<List<ArticleEntity>> = newsDao.searchArticles(query)
}