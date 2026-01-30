package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boltfooddemo.R
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.presentation.ui.theme.Green217
import com.example.boltfooddemo.presentation.ui.theme.LightGray
import kotlin.collections.filter

@Composable
fun SearchScreen(
    restaurants: List<Restaurant>,
    searchMenuText: String,
    isFav: (Restaurant) -> Boolean,
    onInsertOrDelete: (Restaurant) -> Unit,
    onValueChange: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToInfoScreen: (Restaurant) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    val searchMenuTextFieldWeight by animateFloatAsState(
        targetValue = if (isFocused) 0.8f else 1f,
        animationSpec = tween(durationMillis = 500)
    )
    val textButtonWeight by animateFloatAsState(
        targetValue = if (isFocused) 0.2f else 0.0001f,
        animationSpec = tween(durationMillis = 500)
    )
    var restaurantsList by remember { mutableStateOf(restaurants) }
    var showSearchMenu by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (!showSearchMenu) {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 8.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.TopCenter),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            text = "Food, restaurants, stores...",
                            color = Color.Gray
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Green217,
                        unfocusedBorderColor = LightGray,
                        cursorColor = Green217,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = LightGray
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .onFocusChanged {
                            if (it.isFocused) {
                                showSearchMenu = true
                            }
                        }
                )
            }

            Text(
                text = "Search for restaurants",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 8.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.TopCenter),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchMenuText,
                    onValueChange = { it ->
                        onValueChange(it)
                        restaurantsList = restaurants.filter { it.restaurantName.contains(searchMenuText, ignoreCase = true) }
                    },
                    placeholder = { Text("Search") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    trailingIcon = {
                        if (searchMenuText.isNotEmpty()) {
                            IconButton(onClick = {
                                onValueChange("")
                                restaurantsList = restaurants.filter { it.restaurantName.contains(searchMenuText, ignoreCase = true) }
                            }) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = null)
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusManager.clearFocus()
                        }
                    ),
                    modifier = Modifier.weight(searchMenuTextFieldWeight).animateContentSize(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Green217,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = LightGray,
                        cursorColor = Green217
                    )
                )
                TextButton(
                    onClick = {
                        focusManager.clearFocus()
                        onNavigateBack()
                    },
                    modifier = Modifier.weight(textButtonWeight).animateContentSize()
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }

            LazyColumn(
                modifier = modifier.fillMaxSize().padding(top = 100.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(restaurantsList) { restaurant ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable{ onNavigateToInfoScreen(restaurant) }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(12.dp))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bolt_food),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )

                            IconButton(
                                onClick = {onInsertOrDelete(restaurant)},
                                modifier = Modifier.align(Alignment.TopEnd)
                            ) {
                                Icon(
                                    imageVector = if (isFav(restaurant)) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                    contentDescription = null,
                                    tint = if (isFav(restaurant)) Color.Red else Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = restaurant.restaurantName,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}