package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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


@Composable
fun ProductsView(navController: NavController, productViewModel: ProductViewModel) {
    val productArray: List<Aproduct> = listOf(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        ),
        Aproduct(
            "RedBull2", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        ),

        )
    LazyColumn(
        modifier = Modifier
            .padding(top = 5.dp)
            .padding(10.dp)
    ) {
        items(items = productArray) { product ->
            ProductItem(product = product, navController, productViewModel)
        }
    }
}

@Composable
fun ProductItem(product: Aproduct, navController: NavController, productViewModel: ProductViewModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.primary,

        modifier = Modifier.padding(top = 5.dp)
    ) {
        Row(
            Modifier
                .clickable { navController.navigate("SingleProduct")
                    productViewModel.currentItem = product }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Image(
                painter = painterResource(id = product.productImage),
                contentDescription = "A redbull",
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))
            Column(Modifier.weight(1f)) {
                Text(text = product.name)
                Text(text = product.description)
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ProductItem(
        product = Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
}

 */
