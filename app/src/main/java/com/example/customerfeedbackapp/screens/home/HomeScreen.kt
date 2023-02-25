package com.example.customerfeedbackapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.composables.BottomBar
import com.example.customerfeedbackapp.composables.UiFab
import com.example.customerfeedbackapp.composables.UiTopAppBar
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme

@Composable
fun HomeScreen(
    //navController: NavController,
    //viewModel: MainViewModel,
    //permissionViewModel: PermissionViewModel
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
/*
@Preview(showBackground = true, widthDp = 320)
@Composable
fun HomeViewPreview() {
    CustomerFeedbackAppTheme {
        HomeView(Modifier.fillMaxSize())
    }
}

 */


/*
All Composable elements used with the Store header.
The idea of the header is to have the name of the store
with their logo or chosen image. Since the app could be used by multiple companies
 */
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


/*
All Composable elements used for store information
 */
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

/*
All Composable elements used for store news
 */
data class NewsArticle(
    val headline: String,
    val body: String
)

@Composable
fun StoreNews(headline: String, body: String) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = headline)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = body)
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
        NewsArticle("rgjdjghdxjkrhgjkdxhrkjghxjkdhrg", "toasters are on salt for this week tbw"),
        NewsArticle("LOREM IPSUM", "LOREM IPSUM SÄLÄ BÄLÄ HÄLÄ TÄLÄ KÄLÄ MÄLÄ JÄL DÄLÄ "),
        NewsArticle("LOREM IPSUM", "LOREM IPSUM SÄLÄ BÄLÄ HÄLÄ TÄLÄ KÄLÄ MÄLÄ JÄL DÄLÄ "),
    ),
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 5.dp)
            .height(350.dp)
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

