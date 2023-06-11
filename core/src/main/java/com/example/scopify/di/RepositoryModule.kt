package com.example.scopify.di

import com.example.scopify.data.repository.NewsRepositoryImpl
import com.example.scopify.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}