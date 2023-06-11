package com.example.scopify.data.data_source.local

import com.example.scopify.data.data_source.local.entity.ArticleEntity
import com.example.scopify.data.data_source.local.entity.SourceEntity
import com.example.scopify.data.data_source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val newsDao: NewsDao) {
    fun getAllSources(): Flow<List<SourceEntity>> = newsDao.getAllSources()
    fun getSources(categoryId: String): Flow<List<SourceEntity>> = newsDao.getSources(categoryId)
    suspend fun saveSources(sources: List<SourceEntity>) = newsDao.saveSources(sources)
    fun getArticles(sourceId: String): Flow<List<ArticleEntity>> = newsDao.getArticles(sourceId)
    suspend fun saveArticles(articles: List<ArticleEntity>) = newsDao.saveArticles(articles)
    fun searchSources(query: String): Flow<List<SourceEntity>> = newsDao.searchSources(query)
    fun searchArticles(query: String): Flow<List<ArticleEntity>> = newsDao.searchArticles(query)
}