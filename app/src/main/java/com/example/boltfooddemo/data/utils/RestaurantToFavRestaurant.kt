package com.example.boltfooddemo.data.utils

import com.example.boltfooddemo.data.model.FavRestaurant
import com.example.boltfooddemo.data.model.Restaurant

fun restaurantToFavRestaurant(restaurant: Restaurant): FavRestaurant {
    return FavRestaurant(
        restaurantID = restaurant.restaurantID,
        address = restaurant.address,
        parkingLot = restaurant.parkingLot,
        restaurantName = restaurant.restaurantName,
        type = restaurant.type
    )
}