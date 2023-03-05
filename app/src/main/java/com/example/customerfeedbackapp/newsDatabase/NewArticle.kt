package com.example.customerfeedbackapp.newsDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "article_database")
data class NewsArticle(

    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "news_title") val newsTitle: String?,
    @ColumnInfo(name = "news_article") val newsArticle: String?,
)