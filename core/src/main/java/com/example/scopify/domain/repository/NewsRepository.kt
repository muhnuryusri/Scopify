package com.example.scopify.domain.repository

import com.example.scopify.data.Resource
import com.example.scopify.domain.entity.Article
import com.example.scopify.domain.entity.Category
import com.example.scopify.domain.entity.Source
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getCategories(): Flow<Resource<List<Category>>>
    fun getSources(categoryId: String): Flow<Resource<List<Source>>>
    fun getArticles(sourceId: String): Flow<Resource<List<Article>>>
    fun searchSources(query: String): Flow<Resource<List<Source>>>
    fun searchArticles(query: String): Flow<Resource<List<Article>>>
}