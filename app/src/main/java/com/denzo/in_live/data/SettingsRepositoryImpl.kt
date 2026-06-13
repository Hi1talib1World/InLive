package com.denzo.in_live.data

import android.content.Context
import android.content.SharedPreferences
import com.denzo.in_live.domain.ISettingsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SettingsRepositoryImpl(context: Context) : ISettingsRepository {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "app_settings_prefs", Context.MODE_PRIVATE
    )

    override fun getThemeIsDark(): Flow<Boolean> = createFlow("pref_dark_theme", false)

    override fun getIsMetricSystem(): Flow<Boolean> = createFlow("pref_metric_system", true)

    override fun getNotificationsEnabled(): Flow<Boolean> = createFlow("pref_notifications", true)

    override suspend fun saveThemeIsDark(isDark: Boolean) {
        prefs.edit().putBoolean("pref_dark_theme", isDark).apply()
    }

    override suspend fun saveIsMetricSystem(metric: Boolean) {
        prefs.edit().putBoolean("pref_metric_system", metric).apply()
    }

    override suspend fun saveNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("pref_notifications", enabled).apply()
    }

    private fun createFlow(key: String, defaultValue: Boolean): Flow<Boolean> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { p, k ->
            if (key == k) trySend(p.getBoolean(k, defaultValue))
        }
        prefs.registerOnSharedPreferenceChangeListener(listener)
        trySend(prefs.getBoolean(key, defaultValue))
        awaitClose { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
    }
}
