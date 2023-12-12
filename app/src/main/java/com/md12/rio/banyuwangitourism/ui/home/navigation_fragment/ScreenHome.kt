package com.md12.rio.banyuwangitourism.ui.home.navigation_fragment

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.md12.rio.banyuwangitourism.data.local.TourismEntity
import com.md12.rio.banyuwangitourism.ui.home.view_model.ViewModelHome
import com.md12.rio.banyuwangitourism.ui.ui_components.AvailableContent
import com.md12.rio.banyuwangitourism.ui.ui_components.ComponentEmpty
import com.md12.rio.banyuwangitourism.ui.ui_components.ComponentError
import com.md12.rio.banyuwangitourism.ui.ui_components.ComponentLoading
import com.md12.rio.banyuwangitourism.ui.ui_components.ComponentSearchBar
import com.md12.rio.banyuwangitourism.utils.interface_utils.StateInterface

@Composable
fun ScreenHome(navController: NavController, scaffoldState: ScaffoldState) {
    val homeViewModel = hiltViewModel<ViewModelHome>()
    val homeState by homeViewModel.stateHome

    homeViewModel.allTourism.collectAsState(StateInterface.Loading).value.let { uiState ->
        when (uiState) {
            is StateInterface.Loading -> ComponentLoading()
            is StateInterface.Error -> ComponentError()
            is StateInterface.Success -> {
                HomeContent(
                    listTourism = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    query = homeState.query,
                    onQueryChange = homeViewModel::onQueryChange,
                    onUpdateFavorite = homeViewModel::updateFavorite
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    listTourism: List<TourismEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavorite: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        ComponentSearchBar(query = query, onQueryChange = onQueryChange)
        when (listTourism.isEmpty()) {
            true -> ComponentEmpty()
            false -> AvailableContent(listTourism, navController, scaffoldState, onUpdateFavorite)
        }
    }
}

