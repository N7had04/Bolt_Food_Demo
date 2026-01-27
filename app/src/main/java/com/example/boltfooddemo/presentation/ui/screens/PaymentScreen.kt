package com.example.boltfooddemo.presentation.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.presentation.ui.theme.Green217

@Composable
fun PaymentScreen(
    totalCost: String,
    restaurant: Restaurant,
    onNavigateBack: () -> Unit,
    saveOrder: () -> Unit,
    modifier: Modifier = Modifier
) {
    val showDialog = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf("Delivery") }

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 40.dp, start = 20.dp, end = 20.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable {
                    showDialog.value = true
                }
            )

            Spacer(
                modifier = Modifier.width(60.dp)
            )

            Text(
                text = restaurant.restaurantName,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Delivery or pickup?",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DeliveryDining,
                            contentDescription = null,
                            modifier = Modifier.weight(0.1f)
                        )
                        Spacer(
                            modifier = Modifier.weight(0.05f)
                        )
                        Text(
                            text = "Delivery",
                            color = Color.Black,
                            fontSize = 20.sp,
                            modifier = Modifier.weight(0.7f)
                        )
                        RadioButton(
                            selected = selectedOption.value == "Delivery",
                            onClick = { selectedOption.value = "Delivery" },
                            modifier = Modifier.weight(0.15f),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Green217
                            )
                        )
                    }

                    HorizontalDivider(
                        color = Color.LightGray,
                        thickness = 2.dp
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.DirectionsWalk,
                            contentDescription = null,
                            modifier = Modifier.weight(0.1f)
                        )
                        Spacer(
                            modifier = Modifier.weight(0.05f)
                        )
                        Text(
                            text = "Pickup",
                            color = Color.Black,
                            fontSize = 20.sp,
                            modifier = Modifier.weight(0.7f)
                        )
                        RadioButton(
                            selected = selectedOption.value == "Pickup",
                            onClick = { selectedOption.value = "Pickup" },
                            modifier = Modifier.weight(0.15f),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Green217
                            )
                        )
                    }

                    HorizontalDivider(
                        color = Color.LightGray,
                        thickness = 2.dp
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )

                        Text(
                            text = "$ $totalCost",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    saveOrder()
                    onNavigateBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green217
                )
            ) {
                Text(
                    text = "Place order",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(
        totalCost = "100.0",
        restaurant = Restaurant(0, "", "", "", ""),
        onNavigateBack = {},
        saveOrder = {}
    )
}