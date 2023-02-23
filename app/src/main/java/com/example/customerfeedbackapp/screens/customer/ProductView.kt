package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.R


data class Aproduct(
    val name: String,
    val description: String,
    val price: String,
    val kiloOrPer: Boolean,
    val category: Int,
    val barcode: Long,
    val productImage: Int,

    )


@Composable
fun ProductsView(navController: NavController) {
    val productArray: List<Aproduct> = listOf(
        Aproduct("RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        ),

        )
    LazyColumn(modifier = Modifier.padding(top = 5.dp)) {
        items(items = productArray) { product ->
            ProductItem(product = product)
        }
    }
}

@Composable
fun ProductItem(product: Aproduct) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp)
            .clickable {  }
    ) {
        Row(
            Modifier
                .padding(16.dp)
                , verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = product.productImage),
                contentDescription ="A redbull",
                modifier = Modifier.size(50.dp)
                )
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))
            Text(text = product.name, Modifier.weight(1f))
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
        }
    }
}
