package com.pexels.android.paging3.video

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pexels.android.PexelsClient
import com.pexels.android.model.photo.PhotoResource

class SearchVideoPagingSource(
    private val client: PexelsClient,
) : PagingSource<Int, PhotoResource>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoResource>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResource> {
        TODO("Not yet implemented")
    }

}
