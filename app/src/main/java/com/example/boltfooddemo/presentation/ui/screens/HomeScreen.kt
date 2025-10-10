package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boltfooddemo.R
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.presentation.ui.theme.Green217
import com.example.boltfooddemo.presentation.ui.theme.LightGray

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    pastOrders: List<Restaurant>,
    restaurants: List<Restaurant>,
    onNavigateToAllScreen: () -> Unit,
    onNavigateToInfoScreen: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
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
            keyboardActions = KeyboardActions(
                onSearch = {}
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            trailingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(imageVector = Icons.Default.Tune, contentDescription = null)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green217,
                unfocusedBorderColor = LightGray,
                cursorColor = Green217,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = LightGray
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 20.dp)
        )

        if (pastOrders.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp).clickable { onNavigateToAllScreen() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order Again",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "All",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null
                    )
                }
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pastOrders) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(130.dp).clickable{ onNavigateToInfoScreen() }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bolt_food),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .padding(bottom = 8.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = it.restaurantName,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }

        LazyColumn {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Explore all places",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(restaurants) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable{ onNavigateToInfoScreen() }
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
                            onClick = {},
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.FavoriteBorder,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = it.restaurantName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        pastOrders = listOf(
            Restaurant(0, "address", "parkinglot", "name", "type"),
            Restaurant(1, "address", "parkinglot", "name", "type"),
            Restaurant(2, "address", "parkinglot", "name", "type"),
            Restaurant(3, "address", "parkinglot", "name", "type"),
            Restaurant(4, "address", "parkinglot", "name", "type"),
            Restaurant(5, "address", "parkinglot", "name", "type"),
            Restaurant(6, "address", "parkinglot", "name", "type")
        ),
        restaurants = listOf(
            Restaurant(0, "address", "parkinglot", "name", "type"),
            Restaurant(1, "address", "parkinglot", "name", "type"),
            Restaurant(2, "address", "parkinglot", "name", "type"),
            Restaurant(3, "address", "parkinglot", "name", "type"),
            Restaurant(4, "address", "parkinglot", "name", "type"),
            Restaurant(5, "address", "parkinglot", "name", "type"),
            Restaurant(6, "address", "parkinglot", "name", "type")
        ),
        onNavigateToAllScreen = {},
        onNavigateToInfoScreen = {}
    )
}