package com.example.customerfeedbackapp.screens.owner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.fonts.dmsans_regular
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.newsDatabase.NewsArticle
import com.example.customerfeedbackapp.newsDatabase.NewsArticleViewModel

@Composable
fun ArticleView(navController: NavController) {
    Column() {
        InputCluster(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCluster(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    val mNewsArticleViewModel: NewsArticleViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = stringResource(R.string.article_title), fontFamily = ptserif_bold)
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .heightIn(min = Dp.Unspecified, max = 80.dp),
            shape = RoundedCornerShape(3.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        ) {
            TextField(
                value = title, onValueChange = { title = it },
                label = { Text(text = "An Incredible title!", fontFamily = dmsans_regular) },
                modifier =
                Modifier
                    .height(200.dp)
                    .background(Color.White)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(R.string.article_body), fontFamily = ptserif_bold)
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .heightIn(min = Dp.Unspecified, max = 80.dp),
            shape = RoundedCornerShape(3.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        ) {
            TextField(
                value = body, onValueChange = { body = it },
                label = { Text(text = stringResource(R.string.article_message), fontFamily = dmsans_regular) },
                modifier =
                Modifier
                    .height(200.dp)
                    .background(Color.White)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                mNewsArticleViewModel.addArticle(NewsArticle(0, title, body))
                title = ""
                body = ""
                navController.navigate("ItemMenu")
            },
            shape = RoundedCornerShape(3.dp)

        ) {
            Text(text = stringResource(R.string.article_post), fontFamily = ptserif_bold)
        }
    }
}