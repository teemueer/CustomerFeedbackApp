package com.example.customerfeedbackapp.screens.customer

import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.models.Product
import java.util.*
import kotlin.collections.ArrayList


@Composable
fun ProductsView(navController: NavController, productViewModel: ProductViewModel) {
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
    product: Product,
    navController: NavController,
    productViewModel: ProductViewModel,
    isFeedback: Boolean,
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
                    if (!isFeedback) {
                        navController.navigate("SingleProduct")
                        productViewModel.currentItem = product
                        Log.i("arvo", R.drawable.stock_rb.toString())
                    } else {
                        navController.navigate("FeedbackFormView")
                        productViewModel.currentItem = product
                    }
                }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Image(
                painter = painterResource(id = product.imageId),
                contentDescription = "A redbull",
                modifier = Modifier
                    .size(80.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))
            Column(Modifier.weight(1f)) {
                Text(text = product.name)
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
    state: MutableState<TextFieldValue>,
    isFeedback: Boolean,
) {
    //val listOfProducts = productViewModel.getProducts()
    var filteredList: List<Product>

    LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(min = Dp.Unspecified, max = 625.dp)) {
        val searchedText = state.value.text
        filteredList = if (searchedText.isEmpty()) {
            //productViewModel.state
            productViewModel.fb.value
        } else {
            val resultList = ArrayList<Product>()
            for (product in productViewModel.state) {
                if (product.name.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(product)
                }
            }
            resultList
        }
        items(items = filteredList) { product ->
            ProductItem(product = product, navController, productViewModel, isFeedback)
        }
    }

}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {

    Card(
        backgroundColor = MaterialTheme.colors.primary
    ){
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
            //backgroundColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}
