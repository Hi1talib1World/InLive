package com.denzo.in_live.domain

import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {
    fun getThemeIsDark(): Flow<Boolean>
    suspend fun saveThemeIsDark(isDark: Boolean)
    
    fun getIsMetricSystem(): Flow<Boolean>
    suspend fun saveIsMetricSystem(isMetric: Boolean)
    
    fun getNotificationsEnabled(): Flow<Boolean>
    suspend fun saveNotificationsEnabled(enabled: Boolean)
}
