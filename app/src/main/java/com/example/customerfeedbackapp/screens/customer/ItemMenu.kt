package com.example.customerfeedbackapp.screens.customer

import androidx.appcompat.view.menu.MenuView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
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
) {
    MenuView(navController)
}

@Composable
fun MenuView(navController: NavController,modifier: Modifier = Modifier
             ) {
    Surface(modifier) {
        Column(
            modifier = modifier
                .padding(18.dp)
        ) {
            MenuItem("Products", navController)
            MenuItem("Feedback", navController)
        }
    }
}

@Composable
fun MenuItem(name:String, navController: NavController){
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(Modifier.padding(24.dp).clickable { navController.navigate(name +"View") }) {
                Text(text = name,Modifier.weight(1f))
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription ="",)
        }
    }
}

