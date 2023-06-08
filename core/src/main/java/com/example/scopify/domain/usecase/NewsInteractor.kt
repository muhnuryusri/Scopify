package com.example.scopify.domain.usecase

import com.example.scopify.data.Resource
import com.example.scopify.domain.entity.Article
import com.example.scopify.domain.entity.Category
import com.example.scopify.domain.entity.Source
import com.example.scopify.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor constructor(private val newsRepository: NewsRepository): NewsUseCase {
    override fun getCategories() = newsRepository.getCategories()

    override fun getSources(categoryId: String) = newsRepository.getSources(categoryId)

    override fun getArticles(sourceId: String) = newsRepository.getArticles(sourceId)

    override fun searchSources(query: String) = newsRepository.searchSources(query)

    override fun searchArticles(query: String) = newsRepository.searchArticles(query)

}