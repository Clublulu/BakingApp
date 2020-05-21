package com.udacity.android.bakingapp.ui.detail;

import android.content.Intent;

import androidx.fragment.app.FragmentManager;

/**
 * Click listener handling the Next Step button action, taking the user one Step forward.
 *
 */
public class NextStepClickListener extends BaseStepNavigationClickListener {


    public NextStepClickListener(FragmentManager fragmentManager, Intent intent) {
        super(fragmentManager, intent);
    }

    @Override
    void determineStep() {
        // increment steps unless reached end, then start from beginning
        if (getCurrentStep() != getMaxStepsSize()) {
            setCurrentStep(getCurrentStep() + ONE_CONSTANT);
        } else {
            setCurrentStep(FIRST_STEP);
        }
    }
}
