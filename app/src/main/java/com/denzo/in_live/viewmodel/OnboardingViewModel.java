package com.denzo.in_live.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.denzo.in_live.Model.OnboardingStep;
import com.denzo.in_live.R;

import java.util.ArrayList;
import java.util.List;

public class OnboardingViewModel extends ViewModel {

    private final MutableLiveData<Integer> _currentStepIndex = new MutableLiveData<>(0);
    public final LiveData<Integer> currentStepIndex = _currentStepIndex;

    private final MutableLiveData<Boolean> _isLoadingTransition = new MutableLiveData<>(false);
    public final LiveData<Boolean> isLoadingTransition = _isLoadingTransition;

    private final List<OnboardingStep> steps = new ArrayList<>();

    public OnboardingViewModel() {
        loadSteps();
    }

    private void loadSteps() {
        steps.add(new OnboardingStep(
                "Welcome to In-Live",
                "Your ultimate destination for live TV and movies.",
                R.drawable.inlive,
                R.color.MatteBlack
        ));
        steps.add(new OnboardingStep(
                "Vast Library",
                "Explore thousands of titles across multiple genres.",
                R.drawable.logo_head,
                R.color.MatteBlack
        ));
        steps.add(new OnboardingStep(
                "Stay Updated",
                "Get notified about the latest releases and live events.",
                R.drawable.main_logo,
                R.color.MatteBlack
        ));
    }

    public List<OnboardingStep> getSteps() {
        return steps;
    }

    public int getStepsCount() {
        return steps.size();
    }

    public void nextStep() {
        Integer current = _currentStepIndex.getValue();
        if (current != null && current < steps.size() - 1) {
            startTransition(() -> _currentStepIndex.setValue(current + 1));
        }
    }

    public void previousStep() {
        Integer current = _currentStepIndex.getValue();
        if (current != null && current > 0) {
            startTransition(() -> _currentStepIndex.setValue(current - 1));
        }
    }

    private void startTransition(Runnable action) {
        _isLoadingTransition.setValue(true);
        // Simulate a short delay for smooth transitions and guarding against click spam
        new android.os.Handler().postDelayed(() -> {
            action.run();
            _isLoadingTransition.setValue(false);
        }, 400);
    }

    public boolean isLastStep() {
        Integer current = _currentStepIndex.getValue();
        return current != null && current == steps.size() - 1;
    }
}
