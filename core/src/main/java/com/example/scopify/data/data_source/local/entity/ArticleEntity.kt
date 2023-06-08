package com.example.scopify.data.data_source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.scopify.data.data_source.remote.response.Source
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class ArticleEntity(
    @ColumnInfo("publishedAt")
    val publishedAt: String? = null,

    @ColumnInfo("author")
    val author: String? = null,

    @ColumnInfo("urlToImage")
    val urlToImage: String? = null,

    @ColumnInfo("description")
    val description: String? = null,

    @PrimaryKey
    @ColumnInfo("source")
    val source: Source? = null,

    @ColumnInfo("title")
    val title: String? = null,

    @ColumnInfo("url")
    val url: String? = null,

    @ColumnInfo("content")
    val content: String? = null
) : Parcelable