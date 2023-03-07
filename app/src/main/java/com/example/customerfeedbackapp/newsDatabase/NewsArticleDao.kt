package com.example.customerfeedbackapp.newsDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsArticleDao {
    @Query("SELECT * FROM article_database")
    fun getAll(): LiveData<List<NewsArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: NewsArticle)

    @Delete
    suspend fun delete(article: NewsArticle)


}