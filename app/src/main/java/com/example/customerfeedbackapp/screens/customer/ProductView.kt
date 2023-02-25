package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.R
import java.util.*
import kotlin.collections.ArrayList


@Composable
fun ProductsView(navController: NavController, productViewModel: ProductViewModel) {
    /*val productArray: List<Aproduct> = listOf(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        ),
        Aproduct(
            "RedBull2", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        ),

        )
    val listOfProducts = getProducts()

     */
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(Modifier.padding(16.dp)) {
        SearchView(state = textState)
        Spacer(modifier = Modifier.height(10.dp))
        ProductList(
            navController = navController,
            productViewModel = productViewModel,
            state = textState,
            false
        )
    }
}


@Composable
fun ProductItem(
    product: Aproduct,
    navController: NavController,
    productViewModel: ProductViewModel,
    isFeedback:Boolean,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.primary,

        modifier = Modifier.padding(top = 10.dp)
    ) {
        Row(
            Modifier
                .clickable {
                    if(!isFeedback) {
                        navController.navigate("SingleProduct")
                        productViewModel.currentItem = product
                    }else{
                        navController.navigate("FeedbackFormView")
                        productViewModel.currentItem = product
                    }
                }
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

fun getProducts(): ArrayList<Aproduct> {
    val listOfProducts = ArrayList<Aproduct>()
    listOfProducts.add(
        Aproduct(
            "RedBull2", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull1", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull3", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )
    listOfProducts.add(
        Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
        )
    )

    return listOfProducts
}

@Composable
fun ProductList(
    navController: NavController,
    productViewModel: ProductViewModel,
    state: MutableState<TextFieldValue>,
    isFeedback:Boolean,
) {
    val listOfProducts = getProducts()
    var filteredList: ArrayList<Aproduct>

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        filteredList = if (searchedText.isEmpty()) {
            listOfProducts
        } else {
            val resultList = ArrayList<Aproduct>()
            for (product in listOfProducts) {
                if (product.name.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(product)
                }
            }
            resultList
        }
        items(items = filteredList) { product ->
            ProductItem(product = product, navController, productViewModel,isFeedback)
        }
    }

}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value -> state.value = value },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(onClick = { state.value = TextFieldValue("") }) {
                    Icon(
                        imageVector = Icons.Default.Close, contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}
