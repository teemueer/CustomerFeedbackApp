package com.example.customerfeedbackapp.composables

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.customerfeedbackapp.fonts.dmsans_bold

@Composable
fun BottomBar(
    navController: NavController,
) {
    var selectedIndex by remember { mutableStateOf(0) }
        NavigationBar(
        ) {
            NavigationBarItem(
                selected = (selectedIndex == 0),
                onClick = {
                    selectedIndex = 0
                    navController.navigate("home")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home"
                    )
                },
                label = { Text(text = "Home", fontFamily = dmsans_bold)}
            )

            NavigationBarItem(
                selected = (selectedIndex == 1),
                onClick = {
                    selectedIndex = 1
                    navController.navigate("ItemMenu")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "List "
                    )
                },
                label = { Text(text = "Menu", fontFamily = dmsans_bold)},
            )
        }
}