package com.denzo.in_live.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.denzo.in_live.Adapter.OnboardingAdapter;
import com.denzo.in_live.MainActivity;
import com.denzo.in_live.R;
import com.denzo.in_live.databinding.ActivityOnboardingBinding;
import com.denzo.in_live.manager.SessionManager;
import com.denzo.in_live.viewmodel.OnboardingViewModel;

public class OnboardingActivity extends AppCompatActivity {

    private ActivityOnboardingBinding binding;
    private OnboardingViewModel viewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        sessionManager = new SessionManager(this);
        if (sessionManager.isFirstRunCompleted()) {
            navigateToMain();
            return;
        }

        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(OnboardingViewModel.class);

        setupViewPager();
        setupIndicators();
        setupListeners();
        observeViewModel();
    }

    private void setupViewPager() {
        OnboardingAdapter adapter = new OnboardingAdapter(viewModel.getSteps());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateIndicators(position);
            }
        });
        // Disable user swipe to enforce button navigation as per guard requirement
        binding.viewPager.setUserInputEnabled(false);
    }

    private void setupIndicators() {
        int count = viewModel.getStepsCount();
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0);

        for (int i = 0; i < count; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.indicator_dot
            ));
            indicators[i].setLayoutParams(params);
            binding.layoutIndicators.addView(indicators[i]);
        }
        updateIndicators(0);
    }

    private void updateIndicators(int index) {
        int childCount = binding.layoutIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) binding.layoutIndicators.getChildAt(i);
            if (i == index) {
                imageView.setColorFilter(ContextCompat.getColor(this, R.color.md_theme_dark_primary));
                imageView.setAlpha(1.0f);
            } else {
                imageView.setColorFilter(ContextCompat.getColor(this, R.color.white));
                imageView.setAlpha(0.5f);
            }
        }
    }

    private void setupListeners() {
        binding.btnNext.setOnClickListener(v -> {
            if (viewModel.isLastStep()) {
                completeOnboarding();
            } else {
                viewModel.nextStep();
            }
        });

        binding.btnBack.setOnClickListener(v -> viewModel.previousStep());

        binding.btnSkip.setOnClickListener(v -> completeOnboarding());
    }

    private void observeViewModel() {
        viewModel.currentStepIndex.observe(this, index -> {
            binding.viewPager.setCurrentItem(index, true);
            
            // Button Morphing Logic
            if (index == viewModel.getStepsCount() - 1) {
                binding.btnNext.setText("Get Started");
            } else {
                binding.btnNext.setText("Next");
            }

            binding.btnBack.setVisibility(index > 0 ? View.VISIBLE : View.INVISIBLE);
        });

        viewModel.isLoadingTransition.observe(this, isLoading -> {
            binding.loadingOverlay.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.btnNext.setEnabled(!isLoading);
            binding.btnBack.setEnabled(!isLoading);
            binding.btnSkip.setEnabled(!isLoading);
        });
    }

    private void completeOnboarding() {
        sessionManager.setFirstRunCompleted(true);
        navigateToMain();
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
