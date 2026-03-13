package com.example.mediahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mediahub.api.ApiFactory
import com.example.mediahub.repository.MediaRepository
import com.example.mediahub.ui.MediaApp
import com.example.mediahub.viewmodel.MediaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Replace with BuildConfig fields or secure local configuration.
        val tmdbBaseUrl = "https://api.themoviedb.org/3/"
        val tmdbToken = "YOUR_TMDB_BEARER_TOKEN"
        val websiteBaseUrl = "https://yourdomain.com/"
        val listId = "default"

        val tmdbApi = ApiFactory.createTmdbApi(tmdbBaseUrl, tmdbToken)
        val websiteApi = ApiFactory.createWebsiteApi(websiteBaseUrl)
        val repository = MediaRepository(tmdbApi, websiteApi)
        val viewModel = MediaViewModel(repository, listId)

        setContent {
            MediaApp(viewModel)
        }
    }
}
