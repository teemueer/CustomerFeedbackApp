package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel

@Composable
fun ItemMenu(
    navController: NavController,
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
    ) {
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable {
                    navController.navigate("${name}View")
                }
        ) {
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = name, modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription ="",)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview(){
    MenuItem(name = "Feedback", navController = NavController(context = LocalContext.current) )
}

