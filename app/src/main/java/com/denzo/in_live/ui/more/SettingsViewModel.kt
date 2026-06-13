package com.denzo.in_live.ui.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denzo.in_live.domain.ISettingsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: ISettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        combine(
            repository.getThemeIsDark(),
            repository.getIsMetricSystem(),
            repository.getNotificationsEnabled()
        ) { dark, metric, notify ->
            SettingsUiState(
                isDarkTheme = dark,
                isMetricSystem = metric,
                notificationsEnabled = notify
            )
        }.onEach { newState ->
            _uiState.update { newState }
        }.launchIn(viewModelScope)
    }

    fun onThemeToggled(isDark: Boolean) {
        performMutation(
            mutation = { repository.saveThemeIsDark(isDark) },
            rollbackValue = { _uiState.value.copy(isDarkTheme = !isDark) },
            errorMessage = "Failed to save theme preference"
        )
    }

    fun onUnitsToggled(isMetric: Boolean) {
        performMutation(
            mutation = { repository.saveIsMetricSystem(isMetric) },
            rollbackValue = { _uiState.value.copy(isMetricSystem = !isMetric) },
            errorMessage = "Failed to save unit system"
        )
    }

    fun onNotificationsToggled(enabled: Boolean) {
        performMutation(
            mutation = { repository.saveNotificationsEnabled(enabled) },
            rollbackValue = { _uiState.value.copy(notificationsEnabled = !enabled) },
            errorMessage = "Failed to update notification settings"
        )
    }

    private fun performMutation(
        mutation: suspend () -> Unit,
        rollbackValue: () -> SettingsUiState,
        errorMessage: String
    ) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isSaving = true) }
                mutation()
            } catch (e: Exception) {
                _uiState.update { rollbackValue() }
                _uiEvent.emit(UiEvent.ShowToast(errorMessage))
            } finally {
                _uiState.update { it.copy(isSaving = false) }
            }
        }
    }
}
