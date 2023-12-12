package com.md12.rio.banyuwangitourism.ui.home.navigation_fragment

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.md12.rio.banyuwangitourism.data.local.TourismEntity
import com.md12.rio.banyuwangitourism.ui.home.view_model.ViewModelFavorite
import com.md12.rio.banyuwangitourism.ui.ui_components.AvailableContent
import com.md12.rio.banyuwangitourism.ui.ui_components.ComponentEmpty
import com.md12.rio.banyuwangitourism.ui.ui_components.ComponentError
import com.md12.rio.banyuwangitourism.ui.ui_components.ComponentLoading
import com.md12.rio.banyuwangitourism.utils.interface_utils.StateInterface

@Composable
fun ScreenFavorite(navController: NavController, scaffoldState: ScaffoldState) {
    val viewModelFavorite = hiltViewModel<ViewModelFavorite>()

    viewModelFavorite.allFavoriteTourism.collectAsState(StateInterface.Loading).value.let { uiState ->
        when (uiState) {
            is StateInterface.Loading -> ComponentLoading()
            is StateInterface.Error -> ComponentError()
            is StateInterface.Success -> {
                FavoriteContent(
                    listFavorite = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavorite = viewModelFavorite::updateFavorite
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavorite: List<TourismEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavorite: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavorite.isEmpty()) {
        true -> ComponentEmpty()
        false -> AvailableContent(listFavorite, navController, scaffoldState, onUpdateFavorite)
    }
}