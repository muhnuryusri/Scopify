package com.example.scopify.data.data_source.local.room

import androidx.room.*
import com.example.scopify.data.data_source.local.entity.ArticleEntity
import com.example.scopify.data.data_source.local.entity.SourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM sources")
    fun getAllSources(): Flow<List<SourceEntity>>

    @Query("SELECT * FROM sources WHERE category = :categoryId")
    fun getSources(categoryId: String): Flow<List<SourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = SourceEntity::class)
    suspend fun saveSources(sources: List<SourceEntity>)

    @Query("SELECT * FROM articles WHERE source_id = :sourceId")
    fun getArticles(sourceId: String): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = ArticleEntity::class)
    suspend fun saveArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM sources WHERE name LIKE '%' || :query || '%'")
    fun searchSources(query: String): Flow<List<SourceEntity>>

    @Query("SELECT * FROM articles WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchArticles(query: String): Flow<List<ArticleEntity>>
}