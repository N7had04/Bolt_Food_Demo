package com.example.boltfooddemo.presentation.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boltfooddemo.R
import com.example.boltfooddemo.presentation.ui.theme.Green217
import com.example.boltfooddemo.presentation.ui.theme.LightGray
import androidx.core.net.toUri

@Composable
fun PhoneScreen(
    modifier: Modifier = Modifier,
    selectedCountryCode: String,
    phoneText: String,
    onValueChange: (String) -> Unit,
    onNavigateToPasswordScreen: () -> Unit,
    onNavigateToCountryCodesScreen: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_bolt_food),
            contentDescription = null
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(LightGray)
                    .clickable { onNavigateToCountryCodesScreen() },
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedCountryCode,
                        color = Color.Black
                    )
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.width(8.dp))
            
            OutlinedTextField(
                value = phoneText,
                onValueChange = { onValueChange(it) },
                placeholder = { Text("Phone number") },
                modifier = Modifier.weight(0.6f),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = LightGray,
                    unfocusedBorderColor = LightGray,
                    focusedContainerColor = Color.White,
                    focusedBorderColor = Green217,
                    cursorColor = Green217
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "If you sign up, ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "Terms & Conditions",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW,
                                "https://bolt.eu/en/legal/?category=delivery".toUri())
                            context.startActivity(intent)
                        }
                    )
                )
                Text(
                    text = " and ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "Privacy Policy",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW,
                                "https://bolt.eu/privacy/?category=delivery".toUri())
                            context.startActivity(intent)
                        }
                    )
                )
                Text(
                    text = " apply.",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                onClick = { onNavigateToPasswordScreen() },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (phoneText.isNotEmpty()) Green217 else LightGray,
                    contentColor = if (phoneText.isNotEmpty()) Color.White else Color.DarkGray
                )
            ) {
                Text(
                    text = "Continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhoneScreenPreview() {
    PhoneScreen(
        selectedCountryCode = "+994",
        onNavigateToPasswordScreen = {},
        onNavigateToCountryCodesScreen = {},
        phoneText = "",
        onValueChange = {}
    )
}