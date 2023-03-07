package com.example.customerfeedbackapp.screens.owner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.customerfeedbackapp.models.Product2
import com.example.customerfeedbackapp.screens.customer.ProductViewModel
import com.example.customerfeedbackapp.screens.customer.SearchView
import java.util.*

@Composable
fun ChartsView(productViewModel: ProductViewModel, navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(Modifier.padding(16.dp)) {
        SearchView(state = textState)
        Spacer(modifier = Modifier.height(10.dp))
        ProductList(
            navController = navController,
            productViewModel = productViewModel,
            state = textState
        )
    }
}

@Composable
fun ProductItem(
    product: Product2,
    navController: NavController,
    productViewModel: ProductViewModel,
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .heightIn(min = Dp.Unspecified, max = 120.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        border = BorderStroke(2.dp, MaterialTheme.colors.primary)
    ) {
        Row(
            Modifier
                .clickable {
                    navController.navigate("ChartView")
                    productViewModel.currentItem2 = product
                }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            AsyncImage(model = product.images?.first(), contentDescription = "",
                modifier = Modifier.size(60.dp))
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))
            Column(Modifier.weight(1f)) {
                Text(text = product.title ?: "")
                //if(product.description.isNotEmpty())Text(text = product.description)
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
        }
    }
}



@Composable
fun ProductList(
    navController: NavController,
    productViewModel: ProductViewModel,
    state: MutableState<TextFieldValue>
) {
    //val listOfProducts = productViewModel.getProducts()
    var filteredList: List<Product2>

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = Dp.Unspecified, max = 625.dp)) {
        val searchedText = state.value.text
        filteredList = if (searchedText.isEmpty()) {
            //productViewModel.state
            productViewModel.fb.value
        } else {
            val resultList = ArrayList<Product2>()
            for (product2 in productViewModel.state) {
                if (product2.title!!.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(product2)
                }
            }
            resultList
        }
        items(items = filteredList) { product ->
            ProductItem(product = product, navController, productViewModel)
        }
    }

}
