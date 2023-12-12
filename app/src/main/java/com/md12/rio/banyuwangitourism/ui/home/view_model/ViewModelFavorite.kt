package com.md12.rio.banyuwangitourism.ui.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.md12.rio.banyuwangitourism.data.local.TourismEntity
import com.md12.rio.banyuwangitourism.data.repository.Repository
import com.md12.rio.banyuwangitourism.utils.interface_utils.StateInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelFavorite @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _allFavoriteTourism = MutableStateFlow<StateInterface<List<TourismEntity>>>(
        StateInterface.Loading)
    val allFavoriteTourism = _allFavoriteTourism.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavoriteTourism()
                .catch { handleError(it) }
                .collect { handleSuccess(it) }
        }
    }

    private fun handleError(error: Throwable) {
        _allFavoriteTourism.value = StateInterface.Error(error.message.toString())
    }

    private fun handleSuccess(data: List<TourismEntity>) {
        _allFavoriteTourism.value = StateInterface.Success(data)
    }

    fun updateFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavorite(id, isFavorite)
        }
    }
}