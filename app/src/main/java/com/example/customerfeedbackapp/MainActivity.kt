package com.example.customerfeedbackapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.customerfeedbackapp.composables.BottomBar
import com.example.customerfeedbackapp.composables.UiTopAppBar
import com.example.customerfeedbackapp.models.User
import com.example.customerfeedbackapp.screens.customer.*
import com.example.customerfeedbackapp.screens.home.HomeScreen
import com.example.customerfeedbackapp.screens.home.OwnerHome
import com.example.customerfeedbackapp.screens.login.LoginScreen
import com.example.customerfeedbackapp.screens.owner.*
import com.example.customerfeedbackapp.screens.settings.SettingsScreen
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
	// main viewmodel containing the user information
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // auto login
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        firebaseUser?.let {
            Log.d("DBG", "logging in...")
            val user = User(it.uid, it.email)
            viewModel.currentUser.postValue(user)
        }

        setContent {
            val user: User? by viewModel.currentUser.observeAsState(null)
            CustomerFeedbackAppTheme {
                val productViewModel: ProductViewModel by viewModels()

                if (user != null) {
                    OwnerFeedbackApp(viewModel,productViewModel)
                } else {
                    CustomerFeedbackApp(viewModel, productViewModel)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerFeedbackApp(
    viewModel: MainViewModel,
    productViewModel: ProductViewModel,
) {
    // navigation items presented as a map which contains the route name and a localized string name
    val navItems = mapOf(
        "Products" to stringResource(R.string.products),
        "Feedback" to stringResource(R.string.feedback)
    )

    val navController = rememberNavController()

    Scaffold(
        topBar = { UiTopAppBar(navController = navController, viewModel = viewModel) },
        bottomBar = { BottomBar(navController = navController) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            )
            {
                NavHost(navController, startDestination = "home") {
                    composable(route = "home") {
                        HomeScreen(/*navController, viewModel*/)
                    }
                    composable(route = "settings") {
                        SettingsScreen(navController, viewModel)
                    }
                    composable(route = "login") {
                        LoginScreen(navController, viewModel)
                    }
                    composable(route = "ItemMenu") {
                        ItemMenu(navController, navItems)
                    }
                    composable(route = "ProductsView") {
                        ProductsView(navController, productViewModel)
                    }
                    composable(route = "SingleProduct") {
                        SingleProduct(productViewModel, navController, viewModel)
                    }
                    composable(route = "FeedbackView") {
                        FeedbackView(productViewModel, navController)
                    }
                    composable(
                        route = "FeedbackFormView"
                    ) {
                        FeedbackFormView(productViewModel, navController)
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerFeedbackApp(
    viewModel: MainViewModel,
    productViewModel: ProductViewModel,
) {
    // navigation items presented as a map which contains the route name and a localized string name
    val navItems = mapOf(
        "Products" to stringResource(R.string.products),
        "New Product" to stringResource(R.string.new_product),
        "New Article" to stringResource(R.string.new_article),
    )

    val navController = rememberNavController()

    Scaffold(
        topBar = { UiTopAppBar(navController = navController, viewModel = viewModel) },
        bottomBar = { BottomBar(navController = navController) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            )
            {
                NavHost(navController, startDestination = "home") {
                    composable(route = "home") {
                        OwnerHome()
                    }
                    composable(route = "settings") {
                        SettingsScreen(navController, viewModel)
                    }
                    composable(route = "login") {
                        LoginScreen(navController, viewModel)
                    }
                    composable(route = "ItemMenu") {
                        ItemMenu(navController, navItems)
                    }
                    composable(route = "New ProductView") {
                        NewProductView(navController, productViewModel)
                    }
                    composable(route = "New ArticleView") {
                        ArticleView(navController)
                    }
                    composable(route = "ProductsView") {
                        ProductsView(navController, productViewModel)
                    }
                    composable(route = "SingleProduct") {
                        SingleProduct(productViewModel, navController, viewModel)
                    }
                    composable(route = "ChartView") {
                        ChartView(productViewModel, navController)
                    }
                    composable(route = "OwnerFeedbackView") {
                        OwnerFeedbackView(productViewModel)
                    }
                }
            }
        }
    )
}

