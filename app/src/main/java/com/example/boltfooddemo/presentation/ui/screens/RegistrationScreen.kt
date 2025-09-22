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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boltfooddemo.presentation.ui.theme.Green217
import com.example.boltfooddemo.presentation.ui.theme.LightGray

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    surname: String,
    onSurnameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToMainScreen: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { onNavigateBack() }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Add profile details",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {onNameChange(it)},
                    label = { Text(text = "First name") },
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = LightGray,
                        focusedBorderColor = Green217,
                        unfocusedBorderColor = LightGray,
                        cursorColor = Green217
                    ),
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = surname,
                    onValueChange = {onSurnameChange(it)},
                    label = { Text(text = "Last name") },
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = LightGray,
                        focusedBorderColor = Green217,
                        unfocusedBorderColor = LightGray,
                        cursorColor = Green217
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {onEmailChange(it)},
                label = { Text(text = "E-mail") },
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = LightGray,
                    focusedBorderColor = Green217,
                    unfocusedBorderColor = LightGray,
                    cursorColor = Green217
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column {
            Text(
                text = "We may send promotions related to our services - you can unsubscribe anytime" +
                        "in Communication preferences under your Profile",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigateToMainScreen() },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty()) Green217 else LightGray,
                    contentColor = if (name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty()) Color.White else Color.DarkGray
                )
            ) {
                Text(
                    text = "Done",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        name = "",
        onNameChange = {},
        surname = "",
        onSurnameChange = {},
        email = "",
        onEmailChange = {},
        onNavigateBack = {},
        onNavigateToMainScreen = {}
    )
}