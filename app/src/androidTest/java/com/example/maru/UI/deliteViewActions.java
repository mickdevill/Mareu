package com.example.maru.UI;


import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.example.maru.R;

import org.hamcrest.Matcher;

public class deliteViewActions implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.remouve);
        // Maybe check for null
        button.performClick();
    }
}
