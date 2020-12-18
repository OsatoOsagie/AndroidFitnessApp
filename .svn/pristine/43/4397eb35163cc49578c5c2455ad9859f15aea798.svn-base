package com.oo115.myapplication;

import android.content.Intent;

import com.oo115.myapplication.WeightTracking.WeightTrackingFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.anything;

public class WeightTrackingTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityRule.getActivity();
    }

    //testing to see if BMI is correct
    @Test
    public void bmi_test() {

        WeightTrackingFragment weightTrackingFragment = new WeightTrackingFragment();

        double weight = weightTrackingFragment.bmi_calc("80", "188");

        assertEquals(23.0, weight);

    }

    //testing to see if the ffmi calculation is correct
    @Test
    public void ffmi_test() {

        WeightTrackingFragment weightTrackingFragment = new WeightTrackingFragment();

        double ffmi = weightTrackingFragment.ffmi_calc("188", "90", "18");

        assertEquals(20.9, ffmi);

    }


    @Test
    public void drawer_weightNav_test() throws InterruptedException {

        mActivityRule.launchActivity(new Intent());
        onView(withId(R.id.btnQuickWork)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout))
                .perform(click());


        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_weight));
        onView(withText("Body Composition")).check(matches(isDisplayed()));

        onView(withId(R.id.imcHelp))
                .perform(click());
        Thread.sleep(2000);
        onView(withText(R.string.BMI_description)).check(matches(isDisplayed()));

        onView(withText("Ok")).perform(click());

        Thread.sleep(2000);
        onView(withId(R.id.ffmiHelp))
                .perform(click());

        onView(withText(R.string.ffmi_description)).check(matches(isDisplayed()));
        Thread.sleep(1000);

        onView(withText("Ok")).perform(click());

    }


    @Test
    public void drawer_addNew_weight_test() throws InterruptedException {

        mActivityRule.launchActivity(new Intent());
        onView(withId(R.id.btnQuickWork)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout))
                .perform(click());


        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_weight));
        onView(withText("Body Composition")).check(matches(isDisplayed()));
        onView(withId(R.id.weightDetailsButton))
                .perform(click());

        onView(withId(R.id.measuredate)).perform(click());
        onView(withText("OK")).perform(click());
//        weightMeasurement
        onView(withId(R.id.weightMeasurement)).perform(typeText("80"));
        onView(withId(R.id.buttonAddMeasure)).perform(click());
        Thread.sleep(2000);
        onView(withText(R.string.measurement_added)).check(matches(isDisplayed()));
        onView(withText("Ok")).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView_weight)).atPosition(0).onChildView(withId(R.id.deleteBtn)).check(matches(isDisplayed())).perform(click());
        onView(withText("OK")).perform(click());
        onView(withText(R.string.profile_updated)).check(matches(isDisplayed()));

//If you want to set it as 2018.
//        onView(withText("2018")).perform(click());
//        checkIfIdIsDisplayedWithText(R.id.input_date_display, "10/27/2015");

    }

    @Test
    public void drawer_addNew_bodyFat_test() throws InterruptedException {

        mActivityRule.launchActivity(new Intent());
        onView(withId(R.id.btnQuickWork)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout))
                .perform(click());


        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_weight));
        onView(withText("Body Composition")).check(matches(isDisplayed()));
        onView(withId(R.id.fat_imgbtn))
                .perform(click());

        onView(withId(R.id.measuredate)).perform(click());
        onView(withText("OK")).perform(click());
//        weightMeasurement
        onView(withId(R.id.weightMeasurement)).perform(typeText("10"));
        onView(withId(R.id.buttonAddMeasure)).perform(click());
        Thread.sleep(2000);
        onView(withText(R.string.measurement_added)).check(matches(isDisplayed()));
//If you want to set it as 2018.
//        onView(withText("2018")).perform(click());
//        checkIfIdIsDisplayedWithText(R.id.input_date_display, "10/27/2015");

    }

    @Test
    public void drawer_addNew_muscle_test() throws InterruptedException {

        mActivityRule.launchActivity(new Intent());
        onView(withId(R.id.btnQuickWork)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout))
                .perform(click());


        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_weight));
        onView(withText("Body Composition")).check(matches(isDisplayed()));
        onView(withId(R.id.muscles_imgBtn))
                .perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.measuredate)).perform(click());

        onView(withText("OK")).perform(click());
//        weightMeasurement
        onView(withId(R.id.weightMeasurement)).perform(typeText("40"));
        onView(withId(R.id.buttonAddMeasure)).perform(click());
        Thread.sleep(2000);
        onView(withText(R.string.measurement_added)).check(matches(isDisplayed()));
//If you want to set it as 2018.
//        onView(withText("2018")).perform(click());
//        checkIfIdIsDisplayedWithText(R.id.input_date_display, "10/27/2015");

    }


    @Test
    public void drawer_addNew_water_test() throws InterruptedException {

        mActivityRule.launchActivity(new Intent());
        onView(withId(R.id.btnQuickWork)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout))
                .perform(click());


        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_weight));
        onView(withText("Body Composition")).check(matches(isDisplayed()));
        onView(withId(R.id.water_imgBtn))
                .perform(click());

        onView(withId(R.id.measuredate)).perform(click());
        onView(withText("OK")).perform(click());
//        weightMeasurement
        onView(withId(R.id.weightMeasurement)).perform(typeText("50"));
        onView(withId(R.id.buttonAddMeasure)).perform(click());
        Thread.sleep(2000);
        onView(withText(R.string.measurement_added)).check(matches(isDisplayed()));
//If you want to set it as 2018.
//        onView(withText("2018")).perform(click());
//        checkIfIdIsDisplayedWithText(R.id.input_date_display, "10/27/2015");

    }


//

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

}
