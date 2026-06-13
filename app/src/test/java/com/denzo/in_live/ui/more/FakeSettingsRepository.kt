package com.denzo.in_live.ui.more

import com.denzo.in_live.domain.ISettingsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSettingsRepository(private val shouldFail: Boolean = false) : ISettingsRepository {
    private val theme = MutableStateFlow(false)
    private val metric = MutableStateFlow(true)
    private val notify = MutableStateFlow(true)

    override fun getThemeIsDark(): Flow<Boolean> = theme
    override fun getIsMetricSystem(): Flow<Boolean> = metric
    override fun getNotificationsEnabled(): Flow<Boolean> = notify

    override suspend fun saveThemeIsDark(isDark: Boolean) {
        delay(100) // Simulating I/O
        if (shouldFail) throw Exception("Disk Full")
        theme.value = isDark
    }

    override suspend fun saveIsMetricSystem(isMetric: Boolean) {
        if (shouldFail) throw Exception("Disk Full")
        metric.value = isMetric
    }

    override suspend fun saveNotificationsEnabled(enabled: Boolean) {
        notify.value = enabled
    }
}
