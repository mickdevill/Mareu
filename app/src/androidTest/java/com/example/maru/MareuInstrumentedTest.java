package com.example.maru;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.maru.VisualSide.MainActivity;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MareuInstrumentedTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void addNewReu() {
        //ouvrir le dialogue et inseré le sujet
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.sujetInAdd)).perform(typeText("le Sujet"));
//metrer l'heur
        onView(withId(R.id.imageButtonTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12, 15));
        onView(withId(android.R.id.button1)).perform(click());
//metrer la date
        onView(withId(R.id.imageButtonDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(2020, 5, 1));
        onView(withId(android.R.id.button1)).perform(click());
        //numéro de la salle
        onView(withId(R.id.roomnumidadd)).perform(typeText("4"));
        //le membre(s)
        onView(withId(R.id.membersinadd)).perform(typeText("trexctvolka@gmail.com"));
        //l'ajou de la reu
        onView(withText("Add New")).perform(click());
        onView(ViewMatchers.withId(R.id.rcvList)).check(matches(hasChildCount(1)));

    }

    @Test
    public void descriprionIsCorect() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("annuler les filtres ->")).perform(click());
        onView(withText("le Sujet - 12:15 - 1/5/2020 - salle n°4")).check(matches(isDisplayed()));
        onView(withText("trexctvolka@gmail.com")).check(matches(isDisplayed()));
    }

    @Test
    public void filterPlace() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Filtrer par la place ->")).perform(click());
        onView(withId(R.id.inputFiltreP)).perform(typeText("5"));
        onView(withText("FILTRER")).perform(click());
        onView(ViewMatchers.withId(R.id.rcvList)).check(matches(hasChildCount(0)));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("annuler les filtres ->")).perform(click());
        onView(ViewMatchers.withId(R.id.rcvList)).check(matches(hasChildCount(1)));
    }

    @Test
    public void filterDate() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Filtrer par la date ->")).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.rcvList)).check(matches(hasChildCount(0)));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("annuler les filtres ->")).perform(click());
        onView(ViewMatchers.withId(R.id.rcvList)).check(matches(hasChildCount(1)));
    }

    @Test
    public void filterTime() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("annuler les filtres ->")).perform(click());
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Filtrer par le temps ->")).perform(click());
        onView(withId(R.id.Timepikerfordialog)).perform(PickerActions.setTime(13, 20));
        onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.rcvList)).check(matches(hasChildCount(0)));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("annuler les filtres ->")).perform(click());
        onView(ViewMatchers.withId(R.id.rcvList)).check(matches(hasChildCount(1)));
    }

    @Test
    public void remouveAll() {
        onView(withId(R.id.reuDelite)).perform(click());
        onView(ViewMatchers.withId(R.id.rcvList)).check(matches(hasChildCount(0)));
    }

}
