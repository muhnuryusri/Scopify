package com.example.scopify.data.data_source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class ArticleEntity(
    @ColumnInfo("publishedAt")
    val publishedAt: String?,

    @ColumnInfo("author")
    val author: String?,

    @ColumnInfo("urlToImage")
    val urlToImage: String?,

    @ColumnInfo("description")
    val description: String?,

    @ColumnInfo("source_id")
    val sourceId: String?,

    @ColumnInfo("source_name")
    val sourceName: String?,

    @PrimaryKey
    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("url")
    val url: String?,

    @ColumnInfo("content")
    val content: String?
) : Parcelable