package com.example.mediahub.model

import com.squareup.moshi.Json

data class TmdbMediaItem(
    val id: Int,
    @Json(name = "media_type") val mediaType: String?,
    val title: String?,
    val name: String?,
    val overview: String?,
    @Json(name = "poster_path") val posterPath: String?
) {
    val displayTitle: String
        get() = title ?: name ?: "Untitled"
}

data class TmdbTrendingResponse(
    val results: List<TmdbMediaItem>
)

data class WebsiteListItem(
    val id: String,
    val tmdbId: Int,
    val mediaType: String,
    val title: String
)

data class WebsiteListResponse(
    val listId: String,
    val name: String,
    val items: List<WebsiteListItem>
)

data class AddWebsiteListItemRequest(
    val tmdbId: Int,
    val mediaType: String,
    val title: String
)
