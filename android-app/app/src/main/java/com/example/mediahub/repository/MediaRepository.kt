package com.example.mediahub.repository

import com.example.mediahub.api.TmdbApi
import com.example.mediahub.api.WebsiteApi
import com.example.mediahub.model.AddWebsiteListItemRequest
import com.example.mediahub.model.TmdbMediaItem
import com.example.mediahub.model.WebsiteListResponse

class MediaRepository(
    private val tmdbApi: TmdbApi,
    private val websiteApi: WebsiteApi
) {
    suspend fun getTrending(): List<TmdbMediaItem> = tmdbApi.getTrending().results

    suspend fun search(query: String): List<TmdbMediaItem> = tmdbApi.search(query).results

    suspend fun getCustomList(listId: String): WebsiteListResponse = websiteApi.getList(listId)

    suspend fun addToCustomList(
        listId: String,
        tmdbItem: TmdbMediaItem
    ): WebsiteListResponse {
        val request = AddWebsiteListItemRequest(
            tmdbId = tmdbItem.id,
            mediaType = tmdbItem.mediaType ?: "movie",
            title = tmdbItem.displayTitle
        )
        return websiteApi.addItem(listId, request)
    }

    suspend fun removeFromCustomList(listId: String, itemId: String): WebsiteListResponse {
        return websiteApi.removeItem(listId, itemId)
    }
}
