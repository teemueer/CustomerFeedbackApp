package com.example.customerfeedbackapp.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.customerfeedbackapp.R
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
                .padding(18.dp)
                .verticalScroll(rememberScrollState())
        ) {
            StoreHeader()
            StoreInformation()
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
            elevation = 10.dp,
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


data class NewsArticle(
    val headline: String,
    val body: String
)

@Composable
fun StoreNews(headline: String, body: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
        ,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.primary

    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(), backgroundColor = Color.Blue
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = headline, color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(), backgroundColor = Color.Blue
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = body, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun StoreNewsFeed(
    modifier: Modifier = Modifier,
    articles: List<NewsArticle> = listOf(
        NewsArticle(
            "A headline",
            "asdasdjhgdxhfgkjdxhklfjghrdughudxlruhghx djhxdrjklhgx djrh gxdhfjklg hxdjkrh jgkxh dfjklgh xkdjr hgjkhx drkjgh x"
        ),
        NewsArticle("Yes", "Data"),
        NewsArticle(
            "rgjdjghdxjkrhgjkdxhrkjghxjkdhrasdasdasdasdasdasdasdg",
            "toasters are on salt for this week tbw"
        ),
        NewsArticle("LOREM IPSUM", "LOREM IPSUM SÄLÄ BÄLÄ HÄLÄ TÄLÄ KÄLÄ MÄLÄ JÄL DÄLÄ "),
        NewsArticle("LOREM IPSUM", "LOREM IPSUM SÄLÄ BÄLÄ HÄLÄ TÄLÄ KÄLÄ MÄLÄ JÄL DÄLÄ "),
    ),
) {
    LazyColumn(
        modifier = modifier
            .height(500.dp)
    ) {
        items(items = articles) { article ->
            StoreNews(headline = article.headline, body = article.body)
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

