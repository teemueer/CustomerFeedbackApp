package com.example.customerfeedbackapp.screens.owner

import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.customerfeedbackapp.newsDatabase.NewsArticle
import com.example.customerfeedbackapp.newsDatabase.NewsArticleViewModel

@Composable
fun ArticleView(navController: NavController){
    Column() {
        InputCluster(navController)
    }
}

@Composable
fun InputCluster(navController: NavController){
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    val context = LocalContext.current
    val mNewsArticleViewModel: NewsArticleViewModel = viewModel()





    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Title:")
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .heightIn(min = Dp.Unspecified, max = 80.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            border = BorderStroke(2.dp, MaterialTheme.colors.primary)
        ) {
            TextField(
                value = title, onValueChange = { title = it },
                label = { Text(text = "A creative title!") }, modifier =
                Modifier
                    .height(200.dp)
                    .background(Color.White)
                    .fillMaxWidth(), colors = TextFieldDefaults
                    .textFieldColors(backgroundColor = MaterialTheme.colors.onPrimary )
            )
        }
        Text(text = "Body:")
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
                label = { Text(text = "Something very creative!") }, modifier =
                Modifier
                    .height(200.dp)
                    .background(Color.White)
                    .fillMaxWidth(), colors = TextFieldDefaults
                    .textFieldColors(backgroundColor = MaterialTheme.colors.onPrimary )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            mNewsArticleViewModel.addArticle(NewsArticle(0,title,body))
            title = ""
            body = ""
            navController.navigate("ItemMenu")
        }) {
            Text(text = "Post")
        }



    }
}