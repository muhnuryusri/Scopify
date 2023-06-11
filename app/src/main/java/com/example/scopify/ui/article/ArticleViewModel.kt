package com.example.scopify.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.scopify.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun getArticle(sourceId: String) = newsUseCase.getArticles(sourceId).asLiveData()
}