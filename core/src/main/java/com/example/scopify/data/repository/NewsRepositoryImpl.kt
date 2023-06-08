package com.example.scopify.data.repository

import com.example.scopify.data.Resource
import com.example.scopify.domain.entity.Article
import com.example.scopify.domain.entity.Category
import com.example.scopify.domain.entity.Source
import com.example.scopify.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl: NewsRepository {
    override fun getCategories(): Flow<Resource<List<Category>>> {
        TODO("Not yet implemented")
    }

    override fun getSources(categoryId: String): Flow<Resource<List<Source>>> {
        TODO("Not yet implemented")
    }

    override fun getArticles(sourceId: String): Flow<Resource<List<Article>>> {
        TODO("Not yet implemented")
    }

    override fun searchSources(query: String): Flow<Resource<List<Source>>> {
        TODO("Not yet implemented")
    }

    override fun searchArticles(query: String): Flow<Resource<List<Article>>> {
        TODO("Not yet implemented")
    }
}