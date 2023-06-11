package com.example.scopify.ui.home.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.scopify.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {
    val category = newsUseCase.getCategories().asLiveData()
}