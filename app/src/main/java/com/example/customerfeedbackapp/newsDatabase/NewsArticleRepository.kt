package com.example.customerfeedbackapp.newsDatabase

import androidx.lifecycle.LiveData

class NewsArticleRepository(private val newsArticleDao: NewsArticleDao) {

    val readAllData: LiveData<List<NewsArticle>> = newsArticleDao.getAll()

    suspend fun addNewsArticle(newsArticle: NewsArticle) {
        newsArticleDao.insert(newsArticle)
    }

    suspend fun deleteArticle(newsArticle: NewsArticle) {
        newsArticleDao.delete(newsArticle)
    }

}