package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.foundation.border
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boltfooddemo.presentation.utils.BottomNavigationBarItem

@Composable
fun BottomNavigationBar(
    isSelected: (String) -> Boolean,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavigationBarItem.Home,
        BottomNavigationBarItem.Store,
        BottomNavigationBarItem.Search,
        BottomNavigationBarItem.Order,
        BottomNavigationBarItem.Account
    )
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.border(1.dp, Color.LightGray)
    ) {
        items.forEach {
            NavigationBarItem(
                selected = isSelected(it.route),
                onClick = { onNavigate(it.route) },
                icon = { Icon(imageVector = it.icon, contentDescription = it.title) },
                label = { Text(text = it.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray,
                    indicatorColor = Color.White
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        isSelected = { false },
        onNavigate = {}
    )
}