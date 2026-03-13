package com.example.mediahub.api

import com.example.mediahub.model.AddWebsiteListItemRequest
import com.example.mediahub.model.WebsiteListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WebsiteApi {
    @GET("api/lists/{listId}")
    suspend fun getList(@Path("listId") listId: String): WebsiteListResponse

    @POST("api/lists/{listId}/items")
    suspend fun addItem(
        @Path("listId") listId: String,
        @Body request: AddWebsiteListItemRequest
    ): WebsiteListResponse

    @DELETE("api/lists/{listId}/items/{itemId}")
    suspend fun removeItem(
        @Path("listId") listId: String,
        @Path("itemId") itemId: String
    ): WebsiteListResponse
}
