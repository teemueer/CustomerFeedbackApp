package com.example.customerfeedbackapp.newsDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NewsArticle::class], version = 2, exportSchema = false)
abstract class NewsArticleDatabase : RoomDatabase() {
    abstract fun newsArticleDao(): NewsArticleDao

    companion object {
        @Volatile
        private var INSTANCE: NewsArticleDatabase? = null

        fun getDatabase(context: Context): NewsArticleDatabase {
                synchronized(this){
                    var instance = INSTANCE
                    if(instance == null) {
                        instance = Room.databaseBuilder(context,
                            NewsArticleDatabase::class.java, "article_database")
                            .fallbackToDestructiveMigration().build()
                        INSTANCE = instance
                    }
                    return instance
                }
        }

    }


}