package com.example.vision.deadlinemanager;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
    public class testingEspresso {
      @Rule
      public ActivityTestRule<MainActivity>TestRule = new ActivityTestRule<>(MainActivity.class);
      @Test
    public void cekButton1(){
          onView(withId(R.id.what_to_do_btn)).check(matches(isClickable()));
      }
    @Test
    public void cekButton2(){
        onView(withId(R.id.add_task_btn)).check(matches(isClickable()));

    }
    @Test
    public void cekButton3(){
        onView(withId(R.id.completed_btn)).check(matches(isClickable()));
    }
    @Test
    public void cekButton4(){
        onView(withId(R.id.settings_btn)).check(matches(isClickable()));
    }

    @Test
    public void cekAddTaskBtn(){
          onView(withId(R.id.add_task_btn)).perform(click());
          onView(withId(R.id.add_btn)).check(matches(isClickable()));
    }

    @Test
    public void cekBtnPickTime(){
        onView(withId(R.id.add_task_btn)).perform(click());
        onView(withId(R.id.picktime_btn)).check(matches(isClickable()));
    }
    @Test
    public void cekBtnPickDate(){
        onView(withId(R.id.add_task_btn)).perform(click());
        onView(withId(R.id.pickdate_btn)).check(matches(isClickable()));
    }

    @Test
    public void cekTaskNameTextBox(){
        onView(withId(R.id.add_task_btn)).perform(click());
        onView(withId(R.id.taskname_txtBox)).check(matches(isClickable()));
    }

    @Test
    public void cekNotesTextBox(){
        onView(withId(R.id.add_task_btn)).perform(click());
        onView(withId(R.id.notes_txtBox)).check(matches(isClickable()));
    }
}
