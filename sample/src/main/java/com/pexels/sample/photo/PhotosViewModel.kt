package com.pexels.sample.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pexels.android.model.photo.PhotoResource
import com.pexels.sample.Event
import com.pexels.sample.ServiceLocator
import com.pexels.sample.model.ViewItem
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {
    private var _curatedPhotos: MutableLiveData<List<ViewItem>> = MutableLiveData()
    val curatedPhotos: LiveData<List<ViewItem>>
        get() = _curatedPhotos

    private var _curatedEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val curatedEvent: LiveData<Event<String>>
        get() = _curatedEvent

    private var _searchPhotos: MutableLiveData<List<ViewItem>> = MutableLiveData()
    val searchPhotos: LiveData<List<ViewItem>>
        get() = _searchPhotos

    private var _searchEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val searchEvent: LiveData<Event<String>>
        get() = _searchEvent

    init {
        fetchCurated()
    }

    private fun transform(resource: PhotoResource) =
        ViewItem(resource.id, resource.src.medium, resource.url, resource.avgColor)

    fun fetchCurated() {
        viewModelScope.launch {
            try {
                val listPhotosResponse = ServiceLocator.client.curatedPhotos()
                _curatedPhotos.value = listPhotosResponse.photos.map(this@PhotosViewModel::transform)
            } catch (e: Exception) {
                _curatedEvent.value = Event("Exception occurred")
            }
        }
    }

    fun searchFor(query: String) {
        viewModelScope.launch {
            try {
                val listPhotosResponse = ServiceLocator.client.searchForPhotos(query)
                if (listPhotosResponse.photos.isEmpty()) {
                    _searchEvent.value = Event("Empty response")
                } else {
                    _searchPhotos.value = listPhotosResponse.photos.map(this@PhotosViewModel::transform)
                }
            } catch (e: Exception) {
                _searchEvent.value = Event("Exception occurred")
            }
        }
    }
}