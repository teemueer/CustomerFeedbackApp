package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ItemMenu(
    navController: NavController,
    items: List<String>
) {
        MenuView(navController, navList = items)
}

@Composable
fun MenuView(navController: NavController,modifier: Modifier = Modifier
    ,navList:List<String>
    ) {
    Surface(modifier) {
        Column(
            modifier = modifier
                .padding(18.dp)
        ) {
            for (navItem in navList)
                MenuItem(name = navItem, navController = navController)
        }
    }
}

@Composable
fun MenuItem(name: String, navController: NavController) {
    Surface {
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable {
                    navController.navigate("${name}View")
                },
        ) {
            Row(modifier = Modifier.padding(20.dp)) {
                Text(text = name, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.onSecondary)
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    MenuItem(name = "Feedback", navController = NavController(context = LocalContext.current))
}

