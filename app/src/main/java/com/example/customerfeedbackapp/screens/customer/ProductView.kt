package com.example.customerfeedbackapp.screens.customer

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.models.Product2
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.toList
import java.util.*


@Composable
fun ProductsView(navController: NavController, productViewModel: ProductViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(Modifier.padding(start = 16.dp, top = 0.dp, bottom = 0.dp, end = 16.dp)) {
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
    product: Product2,
    navController: NavController,
    productViewModel: ProductViewModel,
    isFeedback: Boolean,
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .heightIn(min = Dp.Unspecified, max = 120.dp),
        shape = RoundedCornerShape(2.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        //border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Row(
            Modifier
                .clickable {
                    if (!isFeedback) {
                        navController.navigate("SingleProduct")
                        productViewModel.currentItem2 = product
                    } else {
                        navController.navigate("FeedbackFormView")
                        productViewModel.currentItem2 = product
                    }
                }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            AsyncImage(
                model = product.images?.first(), contentDescription = "",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))
            Column(Modifier.weight(1f)) {
                Text(text = product.title ?: "", fontFamily = ptserif_bold, fontWeight = FontWeight.Bold)
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
    var filteredList: List<Product2>

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val searchedText = state.value.text
        filteredList = if (searchedText.isEmpty()) {
            productViewModel.fb
        } else {
            val resultList = ArrayList<Product2>()
            for (product2 in productViewModel.fb) {
                if (product2.title!!.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(product2)
                }
            }
            resultList
        }
        items(items = filteredList) { product ->
            ProductItem(product = product, navController, productViewModel, isFeedback)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>) {

    Card(
        shape = RoundedCornerShape(0.dp),
    ) {
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
                // leadingIconColor = Color.White,
                // trailingIconColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = MaterialTheme.colorScheme.secondary,
                focusedLeadingIconColor = Color.White,
                focusedTrailingIconColor = Color.White
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
