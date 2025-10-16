package com.example.boltfooddemo.data.utils

import com.example.boltfooddemo.data.model.FavRestaurant
import com.example.boltfooddemo.data.model.Restaurant

fun favRestaurantToRestaurant(favRestaurant: FavRestaurant): Restaurant {
    return Restaurant(
        restaurantID = favRestaurant.restaurantID,
        address = favRestaurant.address,
        parkingLot = favRestaurant.parkingLot,
        restaurantName = favRestaurant.restaurantName,
        type = favRestaurant.type
    )
}