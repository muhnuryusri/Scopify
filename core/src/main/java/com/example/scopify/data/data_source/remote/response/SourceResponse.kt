package com.example.scopify.data.data_source.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class SourceResponse(

	@field:SerializedName("sources")
	val sources: List<SourcesItem>,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Parcelize
data class SourcesItem(

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("url")
	val url: String
) : Parcelable
