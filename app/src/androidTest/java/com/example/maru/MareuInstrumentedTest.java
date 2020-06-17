package com.example.maru;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.maru.UI.MainActivity;
import com.example.maru.UI.deliteViewActions;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

public class MareuInstrumentedTest {

    //!!!IMPORTANT!!! all tests are independent of others, so if you reed it and work on updating app, respect this rule
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    //the method that just add new meetup with out any assertion.
//used for filters tests, and add meetup test
    public void addMeetUp() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.aboutMeetup)).perform(typeText("Sujet"));
        onView(withId(R.id.imageButtonTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12, 15));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.imageButtonDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(2020, 5, 1));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.room)).perform(click());
        onView(withText("E")).perform(click());
        onView(withId(R.id.membersMails)).perform(typeText("trexctvolka@gmail.com"));
        onView(withId(R.id.save)).perform(click());
    }

    @Test
    public void checkIfRecyclerIsDisplayed() {
        onView(withId(R.id.rcvList)).check(matches(isDisplayed()));
    }

    @Test
    public void addNewMeetup() {
        addMeetUp();

        onView(withId(R.id.rcvList)).check(matches(hasChildCount(1)));

        onView(withId(R.id.remouve)).perform(click());

    }

    @Test
    public void remouveMeetup() {
        addMeetUp();
        onView(withId(R.id.remouve)).perform(click());
        onView(withId(R.id.rcvList)).check(matches(hasChildCount(0)));
    }


    @Test
    public void filterByDate() {
        //use the addMeetup, then add another one to have 2 meetup in list, and be sure the filter work
        //same operation for all filters
        addMeetUp();

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.aboutMeetup)).perform(typeText("Sujet"));
        onView(withId(R.id.imageButtonTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(18, 30));
        onView(withId(android.R.id.button1)).perform(click());
        //here we leave the date insert to android system and samrt datePicker, it will confirm for today
        //the datePicker here is CalendarView and you can't set date on it, but it's the only way to make app support android 5
        onView(withId(R.id.imageButtonDate)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.room)).perform(click());
        onView(withText("F")).perform(click());
        onView(withId(R.id.membersMails)).perform(typeText("trexctvolka@gmail.com"));
        onView(withId(R.id.save)).perform(click());
//very important line of code, it open the menu window
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());

        //and here don't need to insert date yourself, because it's automatic process
        onView(withText(R.string.filter_by_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.rcvList)).check(matches(hasChildCount(1)));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText(R.string.call_off_filters)).perform(click());
        onView(withId(R.id.rcvList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new deliteViewActions()));
        onView(withId(R.id.rcvList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new deliteViewActions()));
    }

    @Test
    public void filterByTime() {
        //in this test we are focused on time, and use timePickers in filter and add activity, so use the pickersActions
        addMeetUp();

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.aboutMeetup)).perform(typeText("Sujet"));
        onView(withId(R.id.imageButtonTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(18, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.imageButtonDate)).perform(click());

        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.room)).perform(click());
        onView(withText("F")).perform(click());
        onView(withId(R.id.membersMails)).perform(typeText("trexctvolka@gmail.com"));
        onView(withId(R.id.save)).perform(click());

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText(R.string.filter_by_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(18, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.rcvList)).check(matches(hasChildCount(1)));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText(R.string.call_off_filters)).perform(click());
        onView(withId(R.id.rcvList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new deliteViewActions()));
        onView(withId(R.id.rcvList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new deliteViewActions()));
    }

    @Test
    public void filterPlace() {
        addMeetUp();

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.aboutMeetup)).perform(typeText("Sujet"));
        onView(withId(R.id.imageButtonTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(18, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.imageButtonDate)).perform(click());

        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.room)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.membersMails)).perform(typeText("trexctvolka@gmail.com"));
        onView(withId(R.id.save)).perform(click());

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        //here we just press ok, because before we create meetup with "A" as room, and this is the default room for this filter
        onView(withText(R.string.filter_by_place)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.rcvList)).check(matches(hasChildCount(1)));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText(R.string.call_off_filters)).perform(click());
        onView(withId(R.id.rcvList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new deliteViewActions()));
        onView(withId(R.id.rcvList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new deliteViewActions()));
    }

    @Test
    public void callOfFilters() {
        addMeetUp();

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.aboutMeetup)).perform(typeText("Sujet"));
        onView(withId(R.id.imageButtonTime)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(18, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.imageButtonDate)).perform(click());

        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.room)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.membersMails)).perform(typeText("trexctvolka@gmail.com"));
        onView(withId(R.id.save)).perform(click());

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText(R.string.filter_by_place)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.rcvList)).check(matches(hasChildCount(1)));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText(R.string.call_off_filters)).perform(click());

        onView(withId(R.id.rcvList)).check(matches(hasChildCount(2)));

        onView(withId(R.id.rcvList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new deliteViewActions()));
        onView(withId(R.id.rcvList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new deliteViewActions()));
    }


}






