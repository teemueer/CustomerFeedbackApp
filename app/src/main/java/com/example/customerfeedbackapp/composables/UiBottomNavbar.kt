package com.example.customerfeedbackapp.composables

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.customerfeedbackapp.fonts.dmsans_bold
import com.example.customerfeedbackapp.R

@Composable
fun BottomBar(
    navController: NavController,
) {
    var selectedIndex by remember { mutableStateOf(0) }
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.secondary
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
                        contentDescription = stringResource(R.string.home),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                label = { Text(text = "Home", fontFamily = dmsans_bold,color = MaterialTheme.colorScheme.onPrimary)}
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
                        contentDescription = stringResource(R.string.menu),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                label = { Text(text = stringResource(R.string.menu), fontFamily = dmsans_bold, color = MaterialTheme.colorScheme.onPrimary)},
            )
        }
}