package com.denzo.in_live.ui.more

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: SettingsViewModel
    private lateinit var fakeRepo: FakeSettingsRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state mirrors repository truth`() = runTest {
        fakeRepo = FakeSettingsRepository()
        viewModel = SettingsViewModel(fakeRepo)
        
        assertEquals(false, viewModel.uiState.value.isDarkTheme)
        assertEquals(true, viewModel.uiState.value.isMetricSystem)
    }

    @Test
    fun `toggle event updates state stream instantly`() = runTest {
        fakeRepo = FakeSettingsRepository()
        viewModel = SettingsViewModel(fakeRepo)
        
        viewModel.onThemeToggled(true)
        
        assertEquals(true, viewModel.uiState.value.isDarkTheme)
    }

    @Test
    fun `save failure triggers state rollback`() = runTest {
        fakeRepo = FakeSettingsRepository(shouldFail = true)
        viewModel = SettingsViewModel(fakeRepo)
        
        // Initial state is false
        viewModel.onThemeToggled(true)
        
        // Should catch error and roll back to false
        assertEquals(false, viewModel.uiState.value.isDarkTheme)
    }
}
