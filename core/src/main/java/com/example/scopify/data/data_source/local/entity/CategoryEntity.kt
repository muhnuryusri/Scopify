package com.example.scopify.data.data_source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.scopify.data.data_source.remote.response.Source
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "categories")
data class CategoryEntity(
    @ColumnInfo("id")
    @PrimaryKey
    val id: String,

    @ColumnInfo("category")
    val category: String
) : Parcelable