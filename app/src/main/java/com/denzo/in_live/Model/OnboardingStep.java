package com.denzo.in_live.Model;

public class OnboardingStep {
    private final String title;
    private final String description;
    private final int imageResId;
    private final int backgroundColor;

    public OnboardingStep(String title, String description, int imageResId, int backgroundColor) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.backgroundColor = backgroundColor;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
