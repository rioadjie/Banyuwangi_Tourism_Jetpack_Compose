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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetail @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _tourism = MutableStateFlow<StateInterface<TourismEntity>>(StateInterface.Loading)
    val tourisms = _tourism.asStateFlow()

    fun getKuliner(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getTourism(id).collect { _tourism.value = StateInterface.Success(it) }
            } catch (e: Exception) {
                _tourism.value = StateInterface.Error(e.message.toString())
            }
        }
    }

    fun updateFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavorite(id, isFavorite)
        }
    }
}