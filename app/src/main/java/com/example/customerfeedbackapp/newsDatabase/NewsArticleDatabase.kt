package com.example.customerfeedbackapp.newsDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NewsArticle::class], version = 1, exportSchema = false)
abstract class NewsArticleDatabase : RoomDatabase() {
    abstract fun newsArticleDao(): NewsArticleDao

    companion object {
        @Volatile
        private var INSTANCE: NewsArticleDatabase? = null

        fun getDatabase(context: Context): NewsArticleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsArticleDatabase::class.java,
                    "article_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }


}