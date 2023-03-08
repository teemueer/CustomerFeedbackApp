package com.example.customerfeedbackapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.fonts.dmsans_regular
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.newsDatabase.NewsArticleViewModel
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme

@Composable
fun HomeScreen(
) {
    HomeView()
}

@Composable
fun HomeView(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 0.dp)
                .background(MaterialTheme.colorScheme.background)

        ) {
            StoreHeader()
            StoreInformation()
            Text(
                text = stringResource(R.string.news),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = ptserif_bold
            )
            StoreNewsFeed()
        }
}

@Composable
fun StoreHeader(
    images: List<Int> = listOf(R.drawable.grocery_store_1_2, R.drawable.grocery_store_2)
) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier,
        ) {
            Image(
                painter = painterResource(id = images[0]),
                contentDescription = stringResource(R.string.grocery_store)
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
            .padding(vertical = 10.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {

            Column(modifier = Modifier.padding(10.dp)) {

                Text(
                    text = headline,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = ptserif_bold
                )


                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = body,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontFamily = dmsans_regular
                )
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
            article.newsTitle?.let {
                article.newsArticle?.let { it1 ->
                    StoreNews(
                        headline = it,
                        body = it1
                    )
                }
            }
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

