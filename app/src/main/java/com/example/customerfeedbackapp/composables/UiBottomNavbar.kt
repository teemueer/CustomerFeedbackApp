package com.example.customerfeedbackapp.composables

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomBar(
    navController: NavController,
) {
    var selectedIndex by remember { mutableStateOf(0) }

    BottomNavigation(elevation = 10.dp, backgroundColor = MaterialTheme.colorScheme.primary) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Navigation button. Return to home"
                )
            },
            label = { Text(text = "Home")},
            selected = (selectedIndex == 0),
            onClick = { selectedIndex = 0
                        navController.navigate("home")
            },
            unselectedContentColor = Color.Black,
            selectedContentColor = MaterialTheme.colorScheme.onPrimary

        )

        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "List "
                )
            },
            label = { Text(text = "Menu")},
            selected = (selectedIndex == 1),
            onClick = {
                selectedIndex = 1
                navController.navigate("ItemMenu")
            },
            unselectedContentColor = Color.Black,
            selectedContentColor = MaterialTheme.colorScheme.onPrimary
        )
    }
}