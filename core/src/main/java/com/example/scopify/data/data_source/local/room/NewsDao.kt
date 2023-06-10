package com.example.scopify.data.data_source.local.room

import androidx.room.*
import com.example.scopify.data.data_source.local.entity.ArticleEntity
import com.example.scopify.data.data_source.local.entity.CategoryEntity
import com.example.scopify.data.data_source.local.entity.SourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = CategoryEntity::class)
    suspend fun saveCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM sources")
    fun getSources(): Flow<List<SourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = SourceEntity::class)
    suspend fun saveSources(sources: List<SourceEntity>)

    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = ArticleEntity::class)
    suspend fun saveArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM sources WHERE name LIKE '%' || :query || '%'")
    fun searchSources(query: String): Flow<List<SourceEntity>>

    @Query("SELECT * FROM articles WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchArticles(query: String): Flow<List<ArticleEntity>>
}