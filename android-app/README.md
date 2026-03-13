# MediaHub Android App Starter

This starter shows how to build an Android app that:

1. Pulls films and TV shows from **TMDB**.
2. Pulls custom lists from **your website API**.
3. Lets users add/remove list items in the app.
4. Pushes list changes back to your website API.

## Architecture

- **Jetpack Compose** for UI.
- **Retrofit + OkHttp + Moshi** for networking.
- **MVVM** (Repository + ViewModel).
- `MediaRepository` orchestrates TMDB and Website API requests.

## APIs required

### TMDB
- `GET /trending/all/day`
- `GET /search/multi`

You must provide a TMDB bearer token and include it in the `Authorization` header.

### Website API (example)
- `GET /api/lists/{listId}` → fetch custom list
- `POST /api/lists/{listId}/items` → add item
- `DELETE /api/lists/{listId}/items/{itemId}` → remove item

## Setup

1. Create an Android Studio project and copy these files into the app module.
2. Add dependencies in `app/build.gradle.kts`:
   - `androidx.lifecycle:lifecycle-viewmodel-compose`
   - `androidx.compose.material3:material3`
   - `com.squareup.retrofit2:retrofit`
   - `com.squareup.retrofit2:converter-moshi`
   - `com.squareup.okhttp3:okhttp`
   - `org.jetbrains.kotlinx:kotlinx-coroutines-android`
3. Supply secrets via `BuildConfig` or local properties:
   - `TMDB_BASE_URL=https://api.themoviedb.org/3/`
   - `TMDB_TOKEN=...`
   - `WEBSITE_BASE_URL=https://yourdomain.com/`
4. Wire `MainActivity` to call `MediaApp()`.

## Notes

- Never hardcode API keys in source control.
- Add user auth for website API before production.
- Add paging/caching if list sizes grow.
