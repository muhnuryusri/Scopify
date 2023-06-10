package com.example.scopify.utils

import com.example.scopify.data.data_source.local.entity.ArticleEntity
import com.example.scopify.data.data_source.local.entity.CategoryEntity
import com.example.scopify.data.data_source.local.entity.SourceEntity
import com.example.scopify.data.data_source.remote.response.ArticlesItem
import com.example.scopify.data.data_source.remote.response.SourcesItem
import com.example.scopify.domain.entity.Article
import com.example.scopify.domain.entity.Category
import com.example.scopify.domain.entity.Source

object DataMapper {
    fun mapEntityToDomainCategory(input: List<CategoryEntity>): List<Category> {
        val categories = ArrayList<Category>()
        input.map {
            val category = Category(
                id = it.id,
                category = it.category
            )
            categories.add(category)
        }
        return categories
    }

    fun mapDomainToEntityCategory(input: List<Category>): List<CategoryEntity> {
        val categories = ArrayList<CategoryEntity>()
        input.map {
            val category = CategoryEntity(
                id = it.id,
                category = it.category
            )
            categories.add(category)
        }
        return categories
    }

    fun mapResponseToEntitySource(input: List<SourcesItem>): List<SourceEntity> {
        val sources = ArrayList<SourceEntity>()
        input.map {
            val source = SourceEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                category = it.category,
                country = it.country,
                language = it.language,
                url = it.url
            )
            sources.add(source)
        }
        return sources
    }

    fun mapEntityToDomainSource(input: List<SourceEntity>): List<Source> =
        input.map {
            Source(
                id = it.id,
                name = it.name,
            )
        }

    fun mapResponseToEntityArticle(input: List<ArticlesItem>): List<ArticleEntity> {
        val articles = ArrayList<ArticleEntity>()
        input.map {
            val source = ArticleEntity(
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
            articles.add(source)
        }
        return articles
    }

    fun mapEntityToDomainArticle(input: List<ArticleEntity>): List<Article> =
        input.map {
            Article(
                author = it.author,
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage
            )
        }
}