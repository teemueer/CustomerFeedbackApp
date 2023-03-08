package com.example.customerfeedbackapp.screens.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.fonts.ptserif_bold

@Composable
fun OwnerHome() {
    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text(
            text = stringResource(R.string.news),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = ptserif_bold
        )
        StoreNewsFeed()
    }
}
