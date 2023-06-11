package com.example.scopify.domain.entity

data class Article(
    val author: String?,
    val title: String?,
    val date: String?,
    val url: String? = null,
    val urlToImage: String? = null,
    val sourceId: String?,
    val sourceName: String?
)
