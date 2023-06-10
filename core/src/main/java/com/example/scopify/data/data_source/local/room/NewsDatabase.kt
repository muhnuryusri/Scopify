package com.example.scopify.data.data_source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.scopify.data.data_source.local.entity.ArticleEntity
import com.example.scopify.data.data_source.local.entity.CategoryEntity
import com.example.scopify.data.data_source.local.entity.SourceEntity

@Database(entities = [CategoryEntity::class, SourceEntity::class, ArticleEntity::class], version = 2, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}