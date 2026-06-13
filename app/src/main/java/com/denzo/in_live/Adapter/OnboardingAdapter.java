package com.denzo.in_live.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzo.in_live.Model.OnboardingStep;
import com.denzo.in_live.databinding.ItemOnboardingStepBinding;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private final List<OnboardingStep> steps;

    public OnboardingAdapter(List<OnboardingStep> steps) {
        this.steps = steps;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOnboardingStepBinding binding = ItemOnboardingStepBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new OnboardingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.bind(steps.get(position));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        private final ItemOnboardingStepBinding binding;

        public OnboardingViewHolder(ItemOnboardingStepBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(OnboardingStep step) {
            binding.tvStepTitle.setText(step.getTitle());
            binding.tvStepDescription.setText(step.getDescription());
            binding.ivStepImage.setImageResource(step.getImageResId());
            binding.getRoot().setBackgroundResource(step.getBackgroundColor());
        }
    }
}
