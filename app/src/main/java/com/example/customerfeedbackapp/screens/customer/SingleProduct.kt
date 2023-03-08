package com.example.customerfeedbackapp.screens.customer

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.fonts.dmsans_regular
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.models.Product
import com.example.customerfeedbackapp.models.Product2
import com.example.customerfeedbackapp.models.User

@Composable
fun SingleProduct(
    productViewModel: ProductViewModel,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val user: User? by viewModel.currentUser.observeAsState(null)
    val product = productViewModel.currentItem2
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
        if (product.description != null) InformationCard(
            product.description,
            title = stringResource(R.string.product_description)
        )
        //if(product.product_info.isNotEmpty()) InformationCard(product.product_info, title = "Tuotetiedot")
        if (user == null) {
            FeedbackButton(navController = navController)
        } else {
            ChartButton(navController)
        }
    }
}


@Composable
fun ProductTitle(product: Product2) {
    Column() {
        Text(
            text = product.title!!,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = ptserif_bold,
            fontSize = 24.sp
        )
        Text(
            text = product.ean!!,
            style = MaterialTheme.typography.titleMedium,
            fontFamily = dmsans_regular
            )
    }
}

@Composable
fun ImageAndPrice(product: Product2) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        product.images?.first()?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )
        }
        Spacer(modifier = Modifier.width(40.dp))
        /* Text(
             text = product.price + " " + if (product.kiloOrKpl) "Kilo" else "Kpl",
             style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold)
         )*/
    }
}

@Composable
fun InformationCard(productString: String, title: String) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(3.dp)
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
                .padding(12.dp),

        ) {
            Text(text = title, fontWeight = FontWeight.ExtraBold, fontFamily = ptserif_bold)
            if (expanded) {
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                Text(text = productString, fontFamily = dmsans_regular)
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
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
    Row(
        horizontalArrangement = Arrangement.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
            Button(onClick = {navController.navigate("FeedbackFormView")},
            shape = RoundedCornerShape(3.dp)) {
                    Text(text = stringResource(R.string.rate_product))
        }
    }
}

@Composable
fun ChartButton(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Button(onClick = {navController.navigate("ChartView")},
            shape = RoundedCornerShape(3.dp)) {
            Text(text = stringResource(R.string.charts))
        }
    }
}