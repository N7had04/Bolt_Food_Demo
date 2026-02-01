package com.example.boltfooddemo.presentation.ui.screens

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.boltfooddemo.R
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.data.model.User
import com.example.boltfooddemo.presentation.ui.components.LockScreenOrientation
import com.example.boltfooddemo.presentation.ui.theme.Green217
import com.example.boltfooddemo.presentation.ui.theme.LightGray

@Composable
fun AccountScreen(
    user: User,
    favRestaurants: List<Restaurant>,
    onNavigateToInfoScreen: (Restaurant) -> Unit,
    onNavigateToAllScreen: (String) -> Unit,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val favouritesText = stringResource(R.string.favorite)
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hello, ${user.name}",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (favRestaurants.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth().clickable { onNavigateToAllScreen(favouritesText) },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = favouritesText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.all),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favRestaurants) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.width(200.dp).clickable{ onNavigateToInfoScreen(it) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bolt_food),
                            contentDescription = null,
                            modifier = Modifier
                                .height(150.dp)
                                .width(200.dp)
                                .padding(bottom = 8.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = it.restaurantName,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.profile),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.weight(1.5f)
            )

            Text(
                text = "${user.name} ${user.lastname}",
                fontSize = 20.sp,
                modifier = Modifier.weight(7f)
            )

            Text(
                text = stringResource(R.string.edit),
                fontSize = 20.sp,
                color = Green217,
                modifier = Modifier.weight(1.5f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = LightGray,
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PhoneAndroid,
                contentDescription = null,
                modifier = Modifier.weight(1.5f)
            )

            Text(
                text = user.phone,
                fontSize = 20.sp,
                modifier = Modifier.weight(7f)
            )

            Text(
                text = stringResource(R.string.edit),
                fontSize = 20.sp,
                color = Green217,
                modifier = Modifier.weight(1.5f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = LightGray,
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.MailOutline,
                contentDescription = null,
                modifier = Modifier.weight(1.5f)
            )

            Text(
                text = user.email,
                fontSize = 20.sp,
                modifier = Modifier.weight(7f)
            )

            Text(
                text = stringResource(R.string.edit),
                fontSize = 20.sp,
                color = Green217,
                modifier = Modifier.weight(1.5f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = LightGray,
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onLogout()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.weight(1.5f)
            )

            Text(
                text = stringResource(R.string.logout),
                fontSize = 20.sp,
                color = Color.Red,
                modifier = Modifier.weight(8.5f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = LightGray,
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onDeleteAccount()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.weight(1.5f)
            )

            Text(
                text = stringResource(R.string.delete_account),
                fontSize = 20.sp,
                color = Color.Red,
                modifier = Modifier.weight(8.5f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .clickable{
                    val intent = Intent(Intent.ACTION_VIEW,
                        "https://bolt.eu/az-az/food/courier/?utm_source=boltfood_app".toUri())
                    context.startActivity(intent)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.bolt_courier),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen(
        user = User(0, "Ali", "Maharramli", "alimaharramli07@gmail.com", "+994555555555", "123456789"),
        favRestaurants = listOf(
            Restaurant(0, "address", "parkingLot", "name", "type"),
            Restaurant(1, "address", "parkingLot", "name", "type"),
            Restaurant(2, "address", "parkingLot", "name", "type"),
        ),
        onNavigateToInfoScreen = {},
        onNavigateToAllScreen = {},
        onLogout = {},
        onDeleteAccount = {}
    )
}