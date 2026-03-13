package com.example.mediahub.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    fun createTmdbApi(baseUrl: String, bearerToken: String): TmdbApi {
        val authInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $bearerToken")
                .build()
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TmdbApi::class.java)
    }

    fun createWebsiteApi(baseUrl: String): WebsiteApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WebsiteApi::class.java)
    }
}
