package com.md12.rio.banyuwangitourism.ui.home.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.md12.rio.banyuwangitourism.data.local.TourismEntity
import com.md12.rio.banyuwangitourism.data.repository.Repository
import com.md12.rio.banyuwangitourism.utils.interface_utils.StateInterface
import com.md12.rio.banyuwangitourism.utils.list_data.DataTourism
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelHome @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _allTourism = MutableStateFlow<StateInterface<List<TourismEntity>>>(StateInterface.Loading)
    val allTourism = _allTourism.asStateFlow()

    private val _StateHome = mutableStateOf(StateHome())
    val stateHome: State<StateHome> = _StateHome

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTourism().collect { kuliner ->
                when (kuliner.isEmpty()) {
                    true -> repository.insertAllTourism(DataTourism.tourisms)
                    else -> _allTourism.value = StateInterface.Success(kuliner)
                }
            }
        }
    }

    private fun searchTourism(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchTourism(query)
                .catch { _allTourism.value = StateInterface.Error(it.message.toString()) }
                .collect { _allTourism.value = StateInterface.Success(it) }
        }
    }

    fun updateFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavorite(id, isFavorite)
        }
    }

    fun onQueryChange(query: String) {
        _StateHome.value = _StateHome.value.copy(query = query)
        searchTourism(query)
    }
}

