package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.presentation.ui.theme.Green217
import com.example.boltfooddemo.presentation.ui.theme.LightGray

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    pastOrders: List<Restaurant>
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        stickyHeader {
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
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        pastOrders = emptyList()
    )
}