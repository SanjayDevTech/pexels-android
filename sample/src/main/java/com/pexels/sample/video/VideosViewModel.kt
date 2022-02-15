package com.pexels.sample.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pexels.android.model.video.VideoResource
import com.pexels.sample.Event
import com.pexels.sample.ServiceLocator
import com.pexels.sample.model.ViewItem
import kotlinx.coroutines.launch

class VideosViewModel : ViewModel() {
    private var _popularVideos: MutableLiveData<List<ViewItem>> = MutableLiveData()
    val popularVideos: LiveData<List<ViewItem>>
        get() = _popularVideos

    private var _popularEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val popularEvent: LiveData<Event<String>>
        get() = _popularEvent

    private var _searchVideos: MutableLiveData<List<ViewItem>> = MutableLiveData()
    val searchVideos: LiveData<List<ViewItem>>
        get() = _searchVideos

    private var _searchEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val searchEvent: LiveData<Event<String>>
        get() = _searchEvent

    init {
        fetchPopular()
    }

    private fun transform(resource: VideoResource) =
        ViewItem(resource.id, resource.videoPictures[0].picture, resource.url)

    fun fetchPopular() {
        viewModelScope.launch {
            try {
                val listVideosResponse = ServiceLocator.client.popularVideos()
                _popularVideos.value = listVideosResponse.videos.map(this@VideosViewModel::transform)
            } catch (e: Exception) {
                _popularEvent.value = Event("Exception occurred")
            }
        }
    }

    fun searchFor(query: String) {
        viewModelScope.launch {
            try {
                val listVideosResponse = ServiceLocator.client.searchForVideos(query)
                if (listVideosResponse.videos.isEmpty()) {
                    _searchEvent.value = Event("Empty response")
                } else {
                    _searchVideos.value = listVideosResponse.videos.map(this@VideosViewModel::transform)
                }
            } catch (e: Exception) {
                _searchEvent.value = Event("Exception occurred")
            }
        }
    }
}