package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.customerfeedbackapp.R
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme

@Composable
fun SingleProduct(productViewModel: ProductViewModel, navController: NavHostController, ) {
    val product = productViewModel.currentItem
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row {
            Image(painter = painterResource(id = R.drawable.stock_rb), contentDescription = "")
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Column {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
                )
                Text(text = product.EAN.toString(), style = MaterialTheme.typography.caption)
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    text = product.price + " " + if (product.kiloOrKpl) "Kilo" else "Kpl",
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
                )
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 13.dp))
        Text(text = product.description)
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { navController.navigate("FeedbackFormView") }) {
                Column() {
                    Text(text = "Arvioi")
                    Text(text = "Tuote")
                }
            }
        }

    }
}
/*
@Preview(showBackground = true, widthDp = 350)
@Composable
fun SingleProductPreview() {
    CustomerFeedbackAppTheme {
        SingleProduct(
            productViewModel = ProductViewModel(),
        )
    }
}
*/
