package com.example.customerfeedbackapp.screens.customer

import androidx.appcompat.view.menu.MenuView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.composables.BottomBar
import com.example.customerfeedbackapp.composables.UiFab
import com.example.customerfeedbackapp.composables.UiTopAppBar
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme

@Composable
fun ItemMenu(
    navController: NavController,
    viewModel: MainViewModel,
    ){

    Scaffold(

        floatingActionButton = { UiFab(navController, viewModel) },
        topBar = { UiTopAppBar(navController, viewModel) },
        bottomBar = { BottomBar(navController) }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            MenuView()
        }
    }
}

@Composable
fun MenuView(modifier: Modifier = Modifier){
    Surface(modifier) {
        Column(
            modifier = modifier
                .padding(18.dp)
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview(){
    CustomerFeedbackAppTheme() {
        MenuView()
    }
}