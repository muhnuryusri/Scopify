package com.example.scopify.di

import com.example.scopify.data.repository.NewsRepositoryImpl
import com.example.scopify.domain.usecase.NewsInteractor
import com.example.scopify.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNewsUseCase(newsRepositoryImpl: NewsRepositoryImpl): NewsUseCase =
        NewsInteractor(newsRepositoryImpl)
}