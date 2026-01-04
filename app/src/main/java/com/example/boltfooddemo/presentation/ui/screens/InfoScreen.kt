package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.boltfooddemo.R
import com.example.boltfooddemo.data.model.MenuItem
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.presentation.ui.theme.Green217
import com.example.boltfooddemo.presentation.ui.theme.LightGray
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    restaurant: Restaurant,
    menu: List<MenuItem>,
    modifier: Modifier = Modifier,
    isFav: Boolean,
    onInsertOrDelete: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    val showSheet = remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    val isAtTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
        }
    }

    val bgColor by animateColorAsState(
        targetValue = if (isAtTop) Color.Transparent else MaterialTheme.colorScheme.background,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val restaurantNameText = if (isAtTop) "" else restaurant.restaurantName

    val mI = remember { mutableStateOf(MenuItem(0, "", "", "", 0.0, 0, "")) }

    if (showSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {showSheet.value = false},
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    AsyncImage(
                        model = mI.value.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 16.dp, end = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.background)
                            .clickable {
                                scope.launch {
                                    sheetState.hide()
                                    showSheet.value = false
                                }
                            }
                            .align(Alignment.TopEnd),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = mI.value.itemName,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$ ${mI.value.itemPrice}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = mI.value.itemDescription,
                        color = Color.Gray,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    HorizontalDivider(
                        color = LightGray,
                        thickness = 2.dp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            modifier = Modifier
                                .height(60.dp)
                                .weight(0.35f)
                                .clip(RoundedCornerShape(32.dp))
                                .border(
                                    width = 2.dp,
                                    color = LightGray,
                                    shape = RoundedCornerShape(32.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.clickable {

                                }
                            )

                            Text(
                                text = "1",
                                color = Color.Black,
                                fontSize = 24.sp
                            )

                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = null,
                                modifier = Modifier.clickable {

                                }
                            )
                        }

                        Spacer(modifier = Modifier.weight(0.1f))

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .height(60.dp)
                                .weight(0.55f)
                                .clip(RoundedCornerShape(32.dp)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Green217
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Add",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "$ ${mI.value.itemPrice}",
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            state = listState
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.9f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bolt_food),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-24).dp)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = restaurant.restaurantName,
                        color = Green217,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                HorizontalDivider(
                    color = LightGray,
                    thickness = 16.dp
                )
            }

            item {
                Text(
                    text = "Menu",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(menu) { menuItem ->
                Column {
                    Card(
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                mI.value = menuItem
                                showSheet.value = true
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.height(120.dp).width(200.dp)
                            ) {
                                Text(
                                    text = menuItem.itemName,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = menuItem.itemDescription,
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "$ ${menuItem.itemPrice}",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .aspectRatio(1f)
                                    .clip(RoundedCornerShape(12.dp))
                            ) {
                                AsyncImage(
                                    model = menuItem.imageUrl,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )

                                Box(
                                    modifier = Modifier
                                        .padding(bottom = 4.dp, end = 4.dp)
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.background)
                                        .clickable {

                                        }
                                        .align(Alignment.BottomEnd),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                    )
                                }
                            }
                        }
                    }

                    HorizontalDivider(
                        color = LightGray,
                        thickness = 2.dp
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .background(bgColor)
                .padding(top = 30.dp, end = 16.dp, start = 16.dp)
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background)
                    .clickable { onNavigateBack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.weight(0.25f))

            Text(
                text = restaurantNameText,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(4f)
            )

            Spacer(modifier = Modifier.weight(0.25f))

            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background)
                    .clickable { onInsertOrDelete() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    tint = if (isFav) Color.Red else Color.Black,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.weight(0.25f))

            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen(
        restaurant = Restaurant(0, "address", "parkingLot", "Restaurant Name", "type"),
        menu = listOf(
            MenuItem(0, "imageUrl", "itemDescription", "itemName", 10.0, 0, "restaurantName"),
            MenuItem(1, "imageUrl", "itemDescription", "itemName", 10.0, 0, "restaurantName"),
            MenuItem(2, "imageUrl", "itemDescription", "itemName", 10.0, 0, "restaurantName"),
            MenuItem(3, "imageUrl", "itemDescription", "itemName", 10.0, 0, "restaurantName"),
            MenuItem(4, "imageUrl", "itemDescription", "itemName", 10.0, 0, "restaurantName")
        ),
        isFav = true,
        onInsertOrDelete = {},
        onNavigateBack = {}
    )
}