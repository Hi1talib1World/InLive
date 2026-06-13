package com.denzo.in_live.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.denzo.fetcher.Utils.Utils
import com.denzo.in_live.Adapter.MoreAdapter
import com.denzo.in_live.BuildConfig
import com.denzo.in_live.Model.More.MoreModel
import com.denzo.in_live.R
import com.denzo.in_live.data.SettingsRepositoryImpl
import com.denzo.in_live.fragment.InitFragment
import com.google.android.material.materialswitch.MaterialSwitch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoreFragment : InitFragment() {

    private lateinit var viewModel: SettingsViewModel
    private lateinit var adapter: MoreAdapter
    
    private var rvMore: RecyclerView? = null
    private var imageView: ImageView? = null
    private var themeSwitch: MaterialSwitch? = null
    private var notifySwitch: MaterialSwitch? = null
    private var unitSwitch: MaterialSwitch? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_more, container, false)
        
        // Manual binding since we are refactoring away from ButterKnife in this Kotlin file
        rvMore = root.findViewById(R.id.rv_more)
        imageView = root.findViewById(R.id.about_imageView)
        themeSwitch = root.findViewById(R.id.theme_switch)
        notifySwitch = root.findViewById(R.id.notify_switch)
        unitSwitch = root.findViewById(R.id.unit_switch)

        val repository = SettingsRepositoryImpl(requireContext())
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(repository) as T
            }
        })[SettingsViewModel::class.java]

        setupUI(root)
        observeViewModel()
        
        return root
    }

    private fun setupUI(root: View) {
        adapter = MoreAdapter(context, R.layout.holder_more)
        
        imageView?.startAnimation(AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down))
        rvMore?.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left))
        rvMore?.layoutManager = Utils.linear(RecyclerView.VERTICAL)
        rvMore?.adapter = adapter
        
        themeSwitch?.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onThemeToggled(isChecked)
        }
        notifySwitch?.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onNotificationsToggled(isChecked)
        }
        unitSwitch?.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onUnitsToggled(isChecked)
        }

        loadStaticItems()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collectLatest { state ->
                        updateUI(state)
                    }
                }
                launch {
                    viewModel.uiEvent.collect { event ->
                        when (event) {
                            is UiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun updateUI(state: SettingsUiState) {
        // Immediate Edge-Effect: Theme
        val targetMode = if (state.isDarkTheme) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        if (AppCompatDelegate.getDefaultNightMode() != targetMode) {
            AppCompatDelegate.setDefaultNightMode(targetMode)
        }

        // Sync switches without triggering listeners again
        themeSwitch?.isChecked = state.isDarkTheme
        notifySwitch?.isChecked = state.notificationsEnabled
        unitSwitch?.isChecked = state.isMetricSystem

        // Interactivity Guarding
        unitSwitch?.isEnabled = state.notificationsEnabled
    }

    private fun loadStaticItems() {
        val moreModel = arrayOf(
            MoreModel("APP", "Version\n" + BuildConfig.FAKE_VERSION),
            MoreModel("COPYRIGHT", "Copyright @In-Live All rights reserved\nTerms and Policies"),
            MoreModel("Changelog", "Checkout what's new"),
            MoreModel("Check for Updates", "Get latest available apk"),
            MoreModel("Telegram", "Join to report issues and request a feature")
        )
        adapter.setList(moreModel.toList())
    }
}
