package com.example.scopify.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.scopify.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun searchSources(query: String) = newsUseCase.searchSources(query).asLiveData()
    fun searchArticles(query: String) = newsUseCase.searchArticles(query).asLiveData()
}