package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boltfooddemo.presentation.ui.theme.Green217
import com.example.boltfooddemo.presentation.ui.theme.LightGray

@Composable
fun PasswordScreen(
    modifier: Modifier = Modifier,
    isRegistration: Boolean,
    onValueChange: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigate: () -> Unit
) {
    val text = if (isRegistration) "Enter your password to login" else "Set your password"
    val focusManager = LocalFocusManager.current
    val focusRequesters = List(4) { FocusRequester() }
    val values = remember { mutableStateListOf("", "", "", "") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { onNavigateBack() }
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Password",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = text,
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            values.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length == 1) {
                            values[index] = newValue
                            if (index < 3) {
                                focusRequesters[index + 1].requestFocus()
                            } else {
                                onValueChange(values.joinToString(""))
                                focusManager.clearFocus()
                                onNavigate()
                            }
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Green217,
                        unfocusedBorderColor = LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = LightGray
                    ),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequesters[index]),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordScreenPreview() {
    PasswordScreen(
        isRegistration = true,
        onNavigateBack = {},
        onValueChange = {},
        onNavigate = {}
    )
}