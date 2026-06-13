package com.denzo.in_live.ui.more

data class SettingsUiState(
    val isDarkTheme: Boolean = false,
    val isMetricSystem: Boolean = true,
    val notificationsEnabled: Boolean = true,
    val isSaving: Boolean = false
)

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
}
