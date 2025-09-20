package com.example.boltfooddemo.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boltfooddemo.data.model.CountryCode
import kotlin.random.Random

@Composable
fun CountryCodesList(
    countryCodes: List<CountryCode>,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Magenta,
        Color.Cyan,
        Color.Gray,
        Color.Black,
        Color.LightGray,
        Color.DarkGray
    )
) {
    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        items(countryCodes) {
            val color by remember(it.code) {
                mutableStateOf(colors[Random.nextInt(colors.size)])
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onNavigate(it.dialCode) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it.code,
                    color = color,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = it.dialCode,
                    color = Color.Gray,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = it.name,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryCodesListPreview() {
    CountryCodesList(
        countryCodes = listOf(
            CountryCode(
                "AF",
                "+93",
                "Afghanistan"
            ),
            CountryCode(
                "AL",
                "+355",
                "Albania"
            ),
            CountryCode(
                "DZ",
                "+213",
                "Algeria"
            ),
            CountryCode(
                "AD",
                "+376",
                "Andorra"
            ),
            CountryCode(
                "AO",
                "+244",
                "Angola"
            )
        ),
        onNavigate = {}
    )
}