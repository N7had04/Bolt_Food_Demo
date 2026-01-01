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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boltfooddemo.R
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.presentation.ui.components.HomeTopBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    pastOrders: List<Restaurant>,
    restaurants: List<Restaurant>,
    isFav: (Restaurant) -> Boolean,
    onNavigateToAllScreen: (String) -> Unit,
    onNavigateToInfoScreen: (Restaurant) -> Unit,
    onInsertOrDelete: (Restaurant) -> Unit
) {
    var isScrollingUp by remember { mutableStateOf(true) }

    Scaffold(
        topBar = { HomeTopBar(isScrollingUp = isScrollingUp) },
        modifier = modifier.fillMaxSize()
    ) { padding ->

        val listState = rememberLazyListState()

        LaunchedEffect(listState) {
            var previousIndex = listState.firstVisibleItemIndex
            var previousScrollOffset = listState.firstVisibleItemScrollOffset

            snapshotFlow { listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset }
                .collect { (index, offset) ->
                    isScrollingUp = when {
                        index < previousIndex -> true
                        index > previousIndex -> false
                        offset < previousScrollOffset -> true
                        offset > previousScrollOffset -> false
                        else -> isScrollingUp
                    }

                    previousIndex = index
                    previousScrollOffset = offset
                }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            if (pastOrders.isNotEmpty()) {
                item {
                    val orderAgainText = stringResource(R.string.order_again)
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable { onNavigateToAllScreen(orderAgainText) },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = orderAgainText,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.all),
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
                }

                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(pastOrders) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.width(130.dp).clickable{ onNavigateToInfoScreen(it) }
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
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.explore_all_places),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(restaurants) { restaurant ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        isFav = {true},
        pastOrders = listOf(
            Restaurant(0, "address", "parkingLot", "name", "type"),
            Restaurant(1, "address", "parkingLot", "name", "type"),
            Restaurant(2, "address", "parkingLot", "name", "type"),
            Restaurant(3, "address", "parkingLot", "name", "type"),
            Restaurant(4, "address", "parkingLot", "name", "type"),
            Restaurant(5, "address", "parkingLot", "name", "type"),
            Restaurant(6, "address", "parkingLot", "name", "type")
        ),
        restaurants = listOf(
            Restaurant(0, "address", "parkingLot", "name", "type"),
            Restaurant(1, "address", "parkingLot", "name", "type"),
            Restaurant(2, "address", "parkingLot", "name", "type"),
            Restaurant(3, "address", "parkingLot", "name", "type"),
            Restaurant(4, "address", "parkingLot", "name", "type"),
            Restaurant(5, "address", "parkingLot", "name", "type"),
            Restaurant(6, "address", "parkingLot", "name", "type")
        ),
        onNavigateToAllScreen = {},
        onNavigateToInfoScreen = {},
        onInsertOrDelete = {}
    )
}