package com.example.scopify.ui.home.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.scopify.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun getSources(categoryId: String) = newsUseCase.getSources(categoryId).asLiveData()
}