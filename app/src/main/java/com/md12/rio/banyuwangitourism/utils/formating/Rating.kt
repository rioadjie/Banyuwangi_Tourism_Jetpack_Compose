package com.md12.rio.banyuwangitourism.utils.formating

fun Rating(rating: Double): Int = when {
    rating in 0.0..2.0 -> 1
    rating in 2.0..4.0 -> 2
    rating in 4.0..6.0 -> 3
    rating in 6.0..8.0 -> 4
    rating in 8.0..10.0 -> 5
    else -> 0
}
