package com.example.customerfeedbackapp.screens.owner

import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.customerfeedbackapp.newsDatabase.NewsArticle
import com.example.customerfeedbackapp.newsDatabase.NewsArticleViewModel
import com.example.customerfeedbackapp.newsDatabase.NewsArticleViewModelFactory

@Composable
fun ArticleView(){
    Column() {
        InputCluster()
    }
}

@Composable
fun InputCluster(){
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    val context = LocalContext.current
    val mNewsArticleViewModel: NewsArticleViewModel = viewModel(
        factory = NewsArticleViewModelFactory(context.applicationContext as Application)
    )



    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .heightIn(min = Dp.Unspecified, max = 120.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            border = BorderStroke(2.dp, MaterialTheme.colors.primary)
        ) {
            TextField(
                value = title, onValueChange = { title = it },
                label = { Text(text = "Leave feedback here!") }, modifier =
                Modifier
                    .height(200.dp)
                    .background(Color.White)
                    .fillMaxWidth(), colors = TextFieldDefaults
                    .textFieldColors(backgroundColor = MaterialTheme.colors.onPrimary )
            )
        }
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .heightIn(min = Dp.Unspecified, max = 120.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            border = BorderStroke(2.dp, MaterialTheme.colors.primary)
        ) {
            TextField(
                value = body, onValueChange = { body = it },
                label = { Text(text = "Leave feedback here!") }, modifier =
                Modifier
                    .height(200.dp)
                    .background(Color.White)
                    .fillMaxWidth(), colors = TextFieldDefaults
                    .textFieldColors(backgroundColor = MaterialTheme.colors.onPrimary )
            )
        }

        Button(onClick = {mNewsArticleViewModel.addArticle(NewsArticle(0,title,body)) }) {
            Text(text = "Post")
        }



    }
}