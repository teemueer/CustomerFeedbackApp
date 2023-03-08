package com.example.customerfeedbackapp.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.models.User

@Composable
fun SettingsScreen(navController: NavController, viewModel: MainViewModel) {
    val user: User? by viewModel.currentUser.observeAsState(null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (user == null) {
                Row(modifier = Modifier.clickable { navController.navigate("login") }) {
                    Icon(Icons.Filled.AccountBox, stringResource(R.string.login), Modifier.size(40.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.login),
                        fontFamily = ptserif_bold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }

            } else {
                Row(modifier = Modifier.clickable {
                    viewModel.logout()
                    navController.navigate("home")
                }) {
                    Icon(Icons.Filled.AccountBox, stringResource(R.string.logout), Modifier.size(40.dp))
                    Text(
                        text = stringResource(R.string.logout),
                        fontFamily = ptserif_bold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}