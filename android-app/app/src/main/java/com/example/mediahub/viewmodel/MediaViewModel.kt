package com.example.mediahub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediahub.model.TmdbMediaItem
import com.example.mediahub.model.WebsiteListItem
import com.example.mediahub.repository.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MediaUiState(
    val trending: List<TmdbMediaItem> = emptyList(),
    val customListItems: List<WebsiteListItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class MediaViewModel(
    private val repository: MediaRepository,
    private val listId: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(MediaUiState())
    val uiState: StateFlow<MediaUiState> = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            runCatching {
                val trending = repository.getTrending()
                val customList = repository.getCustomList(listId)
                _uiState.value = _uiState.value.copy(
                    trending = trending,
                    customListItems = customList.items,
                    isLoading = false
                )
            }.onFailure { throwable ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = throwable.message ?: "Unknown error"
                )
            }
        }
    }

    fun addItem(item: TmdbMediaItem) {
        viewModelScope.launch {
            runCatching {
                val updated = repository.addToCustomList(listId, item)
                _uiState.value = _uiState.value.copy(customListItems = updated.items)
            }.onFailure { throwable ->
                _uiState.value = _uiState.value.copy(error = throwable.message)
            }
        }
    }

    fun removeItem(itemId: String) {
        viewModelScope.launch {
            runCatching {
                val updated = repository.removeFromCustomList(listId, itemId)
                _uiState.value = _uiState.value.copy(customListItems = updated.items)
            }.onFailure { throwable ->
                _uiState.value = _uiState.value.copy(error = throwable.message)
            }
        }
    }
}
