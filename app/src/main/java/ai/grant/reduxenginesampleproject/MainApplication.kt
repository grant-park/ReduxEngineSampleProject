package ai.grant.reduxenginesampleproject

import ai.grant.reduxenginesampleproject.model.epics.StorePersistEpic
import ai.grant.reduxenginesampleproject.model.AppState
import ai.grant.reduxenginesampleproject.model.reducers.NavigationStateReducer
import ai.grant.reduxenginesampleproject.model.reducers.NotesReducer
import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ai.grant.reduxengine.INSTANCE.initialize
import ai.grant.reduxenginesampleproject.model.epics.StorePersistEpic.Companion.PREFS_NAME
import ai.grant.reduxenginesampleproject.model.epics.StorePersistEpic.Companion.STORE_KEY

@ExperimentalCoroutinesApi
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initialize(
            reducers = listOf(NotesReducer(), NavigationStateReducer()),
            epics = listOf(StorePersistEpic(getSharedPreferences(PREFS_NAME, MODE_PRIVATE))),
            initialState = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .getString(STORE_KEY, null)?.let { Json.decodeFromString(it) } ?: AppState()
        )
    }
}