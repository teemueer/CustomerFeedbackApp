package com.example.customerfeedbackapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.newsDatabase.NewsArticle
import com.example.customerfeedbackapp.newsDatabase.NewsArticleViewModel
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme

@Composable
fun HomeScreen(
) {
    HomeView()
}

@Composable
fun HomeView(modifier: Modifier = Modifier) {
    Surface(modifier) {
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {
            StoreHeader()
            StoreInformation()
            Text(
                text = "News:",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
            StoreNewsFeed()
        }
    }
}

@Composable
fun StoreHeader(
    images: List<Int> = listOf(R.drawable.grocery_store_1, R.drawable.grocery_store_2)
) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier,
        ) {
            Image(
                painter = painterResource(id = images[0]),
                contentDescription = "An image of a grocery store"
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun StoreHeaderPreview() {
    CustomerFeedbackAppTheme {
        Column {
            StoreHeader()
        }
    }
}

@Composable
fun StoreInformation() {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
        }
    }
}

@Composable
fun StoreOpenClosed() {


}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun StoreOCPreview() {
    CustomerFeedbackAppTheme {
        Column {
            StoreOpenClosed()
        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun StoreInformationPreview() {
    CustomerFeedbackAppTheme {
        Column {
            StoreInformation()
        }
    }
}


@Composable
fun StoreNews(headline: String, body: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
        ,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = headline,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge

                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = body,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        )
                }
            }
        }
    }
}

@Composable
fun StoreNewsFeed(
    modifier: Modifier = Modifier,
) {
    LocalContext.current
    val newsArticleView: NewsArticleViewModel = viewModel()
    val articles = newsArticleView.readAllData.observeAsState(listOf()).value

    LazyColumn(
        modifier = modifier
    ) {
        items(items = articles) { article ->
            article.newsTitle?.let { article.newsArticle?.let { it1 -> StoreNews(headline = it, body = it1) } }
        }
    }

}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun StoreNewsPreview() {
    CustomerFeedbackAppTheme {
        Column {
            StoreNewsFeed()
        }
    }
}

