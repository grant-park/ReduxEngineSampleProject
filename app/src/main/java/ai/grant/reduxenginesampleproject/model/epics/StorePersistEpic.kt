package ai.grant.reduxenginesampleproject.model.epics

import ai.grant.reduxengine.core.Action
import ai.grant.reduxengine.core.Epic
import ai.grant.reduxenginesampleproject.model.AppState
import android.content.SharedPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalCoroutinesApi
class StorePersistEpic(private val sharedPreferences: SharedPreferences) : Epic<AppState> {
    override fun map(action: Action, state: AppState): Flow<Action> {
        println(Thread.currentThread().name)
        saveState(state)
        return emptyFlow()
    }

    private fun saveState(appState: AppState) {
        Json.encodeToString(appState)
        val editor = sharedPreferences.edit()
        editor.putString(STORE_KEY, Json.encodeToString(appState))
        editor.apply()
    }

    companion object {
        const val PREFS_NAME = "PREFS_NAME"
        const val STORE_KEY = "ENGINE_KEY"
    }
}