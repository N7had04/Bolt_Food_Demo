package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    searchText: String,
    onValueChange: (String) -> Unit,
    onInsertOrDelete: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToPaymentScreen: (Restaurant, String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    val showSheet = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

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
    val countsOfMI = remember(menu.size) { List(menu.size) { 1 }.toMutableStateList() }
    val ind = remember { mutableIntStateOf(0) }
    val totalCost = remember { mutableDoubleStateOf(0.0) }

    var isFocused by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    val searchTextFieldWeight by animateFloatAsState(
        targetValue = if (isFocused) 0.8f else 1f,
        animationSpec = tween(durationMillis = 500)
    )
    val textButtonWeight by animateFloatAsState(
        targetValue = if (isFocused) 0.2f else 0.0001f,
        animationSpec = tween(durationMillis = 500)
    )
    var menuList by remember(menu) { mutableStateOf(menu) }
    var showSearchMenu by remember { mutableStateOf(false) }

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
                            IconButton(
                                onClick = {
                                    countsOfMI[ind.intValue] -= 1
                                },
                                enabled = countsOfMI[ind.intValue] > 1
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = null
                                )
                            }

                            Text(
                                text = "${countsOfMI[ind.intValue]}",
                                color = Color.Black,
                                fontSize = 24.sp
                            )

                            IconButton(
                                onClick = {
                                    countsOfMI[ind.intValue] += 1
                                },
                                enabled = true
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(0.1f))

                        Button(
                            onClick = {
                                scope.launch {
                                    sheetState.hide()
                                    showSheet.value = false
                                }
                                totalCost.doubleValue += countsOfMI[ind.intValue] * mI.value.itemPrice
                            },
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
                                    text = "$ ${countsOfMI[ind.intValue] * mI.value.itemPrice}",
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {showDialog.value = false},
            title = {
                Text(
                    text = "Empty basket",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to delete all the items in your basket?",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                TextButton(onClick = {onNavigateBack()}) {
                    Text(
                        text = "EMPTY BASKET",
                        color = Green217
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {showDialog.value = false}) {
                    Text(
                        text = "CANCEL",
                        color = Green217
                    )
                }
            }
        )
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

            itemsIndexed(if (showSearchMenu) menuList else menu) { i, menuItem ->
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
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
                                ind.intValue = i
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

                            AsyncImage(
                                model = menuItem.imageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(120.dp)
                                    .aspectRatio(1f)
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        }
                    }

                    HorizontalDivider(
                        color = LightGray,
                        thickness = 2.dp
                    )
                }
            }
        }

        if (showSearchMenu) {
            Row(
                modifier = Modifier.background(Color.White).padding(top = 30.dp, bottom = 8.dp, start = 20.dp, end = 20.dp).fillMaxWidth().height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { it ->
                        onValueChange(it)
                        menuList = menu.filter { it.itemName.contains(searchText, ignoreCase = true) ||
                                it.itemDescription.contains(searchText, ignoreCase = true) } },
                    placeholder = { Text("Search") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    trailingIcon = {
                        if (searchText.isNotEmpty()) {
                            IconButton(onClick = {
                                onValueChange("")
                                menuList = menu.filter { it.itemName.contains(searchText, ignoreCase = true) ||
                                        it.itemDescription.contains(searchText, ignoreCase = true) }
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
                    modifier = Modifier.weight(searchTextFieldWeight).animateContentSize(),
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
                        showSearchMenu = false
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
        } else {
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
                        .clickable {
                            if (totalCost.doubleValue > 0.0) {
                                showDialog.value = true
                            } else {
                                onNavigateBack()
                            }
                        },
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
                        .background(MaterialTheme.colorScheme.background)
                        .clickable { showSearchMenu = true },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        }

        if (totalCost.doubleValue > 0.0) {
            Row(
                modifier = Modifier
                    .padding(bottom = 30.dp, end = 16.dp, start = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        onNavigateToPaymentScreen(restaurant, totalCost.doubleValue.toString())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green217
                    )
                ) {
                    Text(
                        text = "View Basket $ ${totalCost.doubleValue}",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
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
        searchText = "",
        onValueChange = {},
        onInsertOrDelete = {},
        onNavigateBack = {},
        onNavigateToPaymentScreen = {
            _, _ ->
        }
    )
}