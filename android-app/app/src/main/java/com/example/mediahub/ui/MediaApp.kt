package com.example.mediahub.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mediahub.viewmodel.MediaViewModel

@Composable
fun MediaApp(viewModel: MediaViewModel) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Trending from TMDB", style = MaterialTheme.typography.titleLarge)

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.trending.take(10)) { item ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(item.displayTitle, modifier = Modifier.weight(1f))
                    Button(onClick = { viewModel.addItem(item) }) {
                        Text("Add")
                    }
                }
            }
        }

        Text("My Website List", style = MaterialTheme.typography.titleLarge)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.customListItems) { item ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(item.title, modifier = Modifier.weight(1f))
                    Button(onClick = { viewModel.removeItem(item.id) }) {
                        Text("Remove")
                    }
                }
            }
        }

        state.error?.let { error ->
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }
    }
}
