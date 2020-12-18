package com.oo115.myapplication;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class MacroNutrientsTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.oo115.myapplication", appContext.getPackageName());
    }

    private Random rand = new Random();

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Rule
    public FragmentTestRule<RegisterFragment> mFragmentTestRule = new FragmentTestRule<>(RegisterFragment.class);


    public String generateString(Random random, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }


    @Test
    public void macroNutrientCalculation() throws InterruptedException {
        int rand_int2 = rand.nextInt(4);
        mFragmentTestRule.launchActivity(null);
        String a_z = "abcdefghijklmnopqrstxyz";

        String email = generateString(rand, a_z, 6) + "@yahoo.com";
        String password = generateString(rand, a_z, 6);


        // testing that the user can enter their first name correctly
        onView(withId(R.id.firstName)).perform(typeText(generateString(rand, a_z, 6)));
        onView(withId(R.id.lastName)).perform(typeText(generateString(rand, a_z, 6)), closeSoftKeyboard());

        onView(withId(R.id.passwordInputFromUser)).perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.emailInputFromUser)).check(matches(isDisplayed()));
        onView(withId(R.id.emailInputFromUser)).perform(typeText(email), closeSoftKeyboard());

        onView(withId(R.id.confirmPassword)).perform(closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(password), closeSoftKeyboard());


        onView(withId(R.id.signUp)).check(matches(isDisplayed()));
        onView(withId(R.id.signUp)).perform(scrollTo(), click());

        Thread.sleep(1000);
        onView(withText(R.string.registration_success)).check(matches(isDisplayed()));


//        onView(withText(R.string.registration_success)).inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//                .perform(click());


//        onView(withText(R.string.registration_success))
//    .inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//                .perform(click());
        onView(withText("OK")).perform(click());


        Thread.sleep(1000);


        onView(withId(R.id.btnQuickWork)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout))
                .perform(click());
//
        Thread.sleep(3000);
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_calculator));
        Thread.sleep(3000);

        onView(withText("Recommended Daily Intake")).check(matches(isDisplayed()));


        onView(withId(R.id.carbohydrates_actualValue)).check(matches(withText("-")));
        onView(withId(R.id.protein_actual)).check(matches(withText("-")));
        onView(withId(R.id.fats_actualValue)).check(matches(withText("-")));
        onView(withId(R.id.water_actualValue)).check(matches(withText("-")));
        onView(withId(R.id.calories_actualValue)).check(matches(withText("-")));

        onView(withId(R.id.macro_calc)).perform(click());


//        onView(withText("-")).check(matches(isDisplayed()));

    }


}
