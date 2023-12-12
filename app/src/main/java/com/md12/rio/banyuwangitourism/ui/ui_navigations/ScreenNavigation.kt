package com.md12.rio.banyuwangitourism.ui.ui_navigations

sealed class ScreenNavigation(val route: String) {
    object Home : ScreenNavigation("home")
    object Favorite : ScreenNavigation("favorite")
    object Profile : ScreenNavigation("profile")
    object Detail : ScreenNavigation("home/{tourismId}") {
        fun createRoute(tourismId: Int) = "home/$tourismId"
    }
}
