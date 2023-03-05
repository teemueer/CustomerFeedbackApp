package com.example.customerfeedbackapp.newsDatabase

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsArticleViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<NewsArticle>>
    private val repository: NewsArticleRepository

    init {
        val newsArticleDao = NewsArticleDatabase.getDatabase(application).newsArticleDao()
        repository = NewsArticleRepository(newsArticleDao)
        readAllData = repository.readAllData
    }

    fun addArticle(article: NewsArticle) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewsArticle(article)
        }
    }

    fun deleteArticle(article: NewsArticle) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteArticle(article)
        }
    }
}

class NewsArticleViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(NewsArticleViewModel::class.java)) {
            return NewsArticleViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
