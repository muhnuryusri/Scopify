package com.example.scopify.domain.entity

data class Article(
    val author: String?,
    val title: String?,
    val url: String? = null,
    val urlToImage: String? = null
)
