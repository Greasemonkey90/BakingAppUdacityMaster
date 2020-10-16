package com.example.bakingappudacity;


import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class IdlingResourceTest {

    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        idlingResource = mainActivityActivityTestRule.getActivity().getIdlingResource();
    }

    @Test
    public void idlingResourceTest() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException c) {
            c.printStackTrace();

        }
        onView((withId(R.id.recipe_card_RV))).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
    }

    @After
    public void unregisterIdlingResource(){
        if(idlingResource != null){
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}



