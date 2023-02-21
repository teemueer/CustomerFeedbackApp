package com.example.customerfeedbackapp.screens.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.navigation.NavController
import com.example.customerfeedbackapp.CustomerFeedbackApp
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.models.User
import com.example.customerfeedbackapp.permissions.PermissionViewModel
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel,
    permissionViewModel: PermissionViewModel
) {
    val user: User? by viewModel.currentUser.observeAsState(null)
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            permissionViewModel.onPermissionResult(
                permission = Manifest.permission.ACCESS_FINE_LOCATION,
                isGranted = isGranted
            )
        }
    )

    Scaffold(

        floatingActionButton = {
            if (user != null) {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Add, "Add")
                }
            }
        },

        topBar = {
            TopAppBar {
                IconButton(onClick = { navController.navigate("settings") }) {
                    Icon(Icons.Filled.Settings, "Settings")
                }
                Text(user?.email ?: "anonymous")
            }
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            HomeView()
        }
    }
}

@Composable
fun HomeView(modifier: Modifier = Modifier) {
    Surface(modifier) {
        Column(modifier = modifier.padding(16.dp)) {
            StoreHeader()
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
                painter = painterResource(id = images[1]),
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
        modifier = Modifier.padding(vertical = 15.dp)
    ) {
        Card(
            modifier = Modifier,
            elevation = 10.dp,
        ) {
            Text(text = "Open Hours")
        }

    }
}

@Preview(showBackground = true, widthDp = 320)
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
    val headline:String,
    val body: String
)

@Composable
fun StoreNews(headline:String, body:String) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
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
        NewsArticle("A headline","asdasdjhgdxhfgkjdxhklfjghrdughudxlruhghx djhxdrjklhgx djrh gxdhfjklg hxdjkrh jgkxh dfjklgh xkdjr hgjkhx drkjgh x"),
        NewsArticle("Yes","Data"),
        NewsArticle("rgjdjghdxjkrhgjkdxhrkjghxjkdhrg","toasters are on salt for this week tbw"),
        NewsArticle("LOREM IPSUM","LOREM IPSUM SÄLÄ BÄLÄ HÄLÄ TÄLÄ KÄLÄ MÄLÄ JÄL DÄLÄ "),
        NewsArticle("LOREM IPSUM","LOREM IPSUM SÄLÄ BÄLÄ HÄLÄ TÄLÄ KÄLÄ MÄLÄ JÄL DÄLÄ "),
        ),
){
    LazyColumn(modifier = modifier.padding(vertical = 5.dp)){
        items(items = articles){
            article ->
            StoreNews(headline = article.headline, body =article.body )
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

