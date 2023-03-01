package com.example.customerfeedbackapp.screens.customer

import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.customerfeedbackapp.R
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.Product
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme

@Composable
fun SingleProduct(productViewModel: ProductViewModel, navController: NavHostController) {
    val product = productViewModel.currentItem
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ProductTitle(product = product)
        Spacer(modifier = Modifier.height(20.dp))
        ImageAndPrice(product = product)
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        if(product.description.isNotEmpty()) InformationCard(product.description, title = "Tuotekuvaus")
        if(product.product_info.isNotEmpty()) InformationCard(product.product_info, title = "Tuotetiedot")
        FeedbackButton(navController = navController)
    }
}


@Composable
fun ProductTitle(product: Product) {
    Column() {
        Text(
            text = product.name,
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
        )
        Text(text = product.EAN, style = MaterialTheme.typography.caption)
    }
}

@Composable
fun ImageAndPrice(product: Product) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = product.imageId), contentDescription = "",
            modifier = Modifier.height(210.dp)
        )
        Spacer(modifier = Modifier.width(40.dp))
        Text(
            text = product.price + " " + if (product.kiloOrKpl) "Kilo" else "Kpl",
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold)
        )
    }
}

@Composable
fun InformationCard(productString: String, title: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        InformationCardContent(productString, title = title)
    }
}

@Composable
fun InformationCardContent(productString: String, title: String) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = title, fontWeight = FontWeight.ExtraBold)
            if (expanded) {
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                Text(text = productString)
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) {
                    "Show less"
                } else {
                    "Show more"
                }

            )
        }
    }

}


@Preview(showBackground = true, widthDp = 350)
@Composable
fun InformationCardPreview() {
    val test = Product(
        "6413600000822",
        "aaaaaaaaaaaa aaaaaaaaaaa aaaaaaaaaaaa a a aaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaa a a aaaaa",
        2,
        false,
        "Tuote testattavaksi",
        "2,48",
        "Vettä, hiilidioksidia ja kaikkee muuta kivaa",
        R.drawable.hartwall_vichy_1_5l
    )
    InformationCard(productString = test.description, title = "Tuotekuvaus")
}


@Composable
fun FeedbackButton(navController: NavHostController) {
    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 8.dp)) {
        Button(onClick = { navController.navigate("FeedbackFormView") }) {
            Column() {
                Text(text = "Arvioi")
                Text(text = "Tuote")
            }
        }
    }
}
