package com.example.bakingappudacity;


import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class RecipeClickTest {

    @Rule
    public ActivityTestRule<MainActivity> testingClickViews =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testingClickingFirstView() {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.recipe_card_RV)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
    @Test
    public void testingClickingSecondView() {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.recipe_card_RV)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }
    @Test
    public void testingClickingThirdView() {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.recipe_card_RV)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }
    @Test
    public void testingClickingFourthView() {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.recipe_card_RV)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }
}
