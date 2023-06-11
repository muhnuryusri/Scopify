package com.example.scopify.data.data_source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "sources")
data class SourceEntity (
    @field:SerializedName("country")
    val country: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("language")
    val language: String?,

    @PrimaryKey
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("category")
    val category: String?,

    @field:SerializedName("url")
    val url: String?
) : Parcelable