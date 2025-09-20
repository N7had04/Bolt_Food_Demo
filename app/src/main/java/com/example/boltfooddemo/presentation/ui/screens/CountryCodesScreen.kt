package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boltfooddemo.data.model.CountryCode
import com.example.boltfooddemo.presentation.ui.components.CountryCodesList
import com.example.boltfooddemo.presentation.ui.theme.Green217
import com.example.boltfooddemo.presentation.ui.theme.LightGray

@Composable
fun CountryCodesScreen(
    modifier: Modifier = Modifier,
    countryCodes: List<CountryCode>,
    searchCountryCodeText: String,
    onValueChange: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToPhoneScreen: (String) -> Unit
) {
    var codes by rememberSaveable { mutableStateOf(countryCodes) }
    var isFocused by rememberSaveable { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    val searchCountryCodeTextFieldWeight by animateFloatAsState(
        targetValue = if (isFocused) 0.8f else 1f,
        animationSpec = tween(durationMillis = 500)
    )
    val textButtonWeight by animateFloatAsState(
        targetValue = if (isFocused) 0.2f else 0.0001f,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onNavigateBack() },
                modifier = Modifier.weight(0.1f)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }

            Spacer(modifier = Modifier.weight(0.25f))

            Text(
                text = "Country prefix",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.weight(0.65f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(bottom = 8.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchCountryCodeText,
                onValueChange = {
                    onValueChange(it)
                    codes = countryCodes.filter { it.name.contains(searchCountryCodeText, ignoreCase = true) } },
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    if (searchCountryCodeText.isNotEmpty()) {
                        IconButton(onClick = {
                            onValueChange("")
                            codes = countryCodes.filter { it.name.contains(searchCountryCodeText, ignoreCase = true) }
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
                        isFocused = false
                    }
                ),
                modifier = Modifier.weight(searchCountryCodeTextFieldWeight).onFocusChanged(
                    onFocusChanged = { state -> isFocused = state.isFocused }
                ).animateContentSize(),
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
                    isFocused = false
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
        CountryCodesList(
            countryCodes = codes,
            onNavigate = {dialCode -> onNavigateToPhoneScreen(dialCode)}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CountryCodesScreenPreview() {
    CountryCodesScreen(
        countryCodes = emptyList(),
        searchCountryCodeText = "",
        onValueChange = {},
        onNavigateBack = {},
        onNavigateToPhoneScreen = {}
    )
}