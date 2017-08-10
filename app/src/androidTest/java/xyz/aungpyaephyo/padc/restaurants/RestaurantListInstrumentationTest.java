package xyz.aungpyaephyo.padc.restaurants;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import xyz.aungpyaephyo.padc.restaurants.activities.RestaurantListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by aung on 7/21/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RestaurantListInstrumentationTest {

    @Rule
    public ActivityTestRule<RestaurantListActivity> mActivityRule = new ActivityTestRule<>(RestaurantListActivity.class);

    @Test
    public void tapFab() {
        onView(withId(R.id.fab)).perform(click());
        //onView(withText("Submit")).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text),
                withText("Search will come later2")))
                .check(matches(isDisplayed()));
    }
}
