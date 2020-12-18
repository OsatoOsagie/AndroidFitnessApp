package com.oo115.myapplication;

import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class LoginFragmentTest {
    @Rule
    public ActivityTestRule<RegisterAndLoginActivity> mActivityRule =
            new ActivityTestRule<>(RegisterAndLoginActivity.class);

    @Before
    public void setUp() throws Exception {
        Intents.init();


    }

    @After
    public void cleanUp() {
        Intents.release();
    }


    //checking that the correct page loads when the activity starts
    @Test
    public void ensureCorrectPageLoadsInitially() {
        mActivityRule.launchActivity(new Intent());
        intended(hasComponent(RegisterAndLoginActivity.class.getName()));
    }

    //checking that a valid user is able to log into the application
    @Test
    public void valid_user_recognised() {

        onView(withId(R.id.etEmail)).perform(typeText("testingtesting@yahoo.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("123456"), closeSoftKeyboard());

        onView(withId(R.id.btn_Signin))
                .perform(click());
        intended(hasComponent(MainActivity.class.getName()));
        onView(withText("Quick Workout")).check(matches(isDisplayed()));

    }


    @Test
    public void invalid_user_recognised() {
        onView(withId(R.id.etEmail)).perform(typeText("expresso@yahoo.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("exp1100"), closeSoftKeyboard());

        onView(withId(R.id.btn_Signin))
                .perform(click());

        onView(withText(R.string.invalid_user)).check(matches(isDisplayed()));

    }

    @Test
    public void invalid_email() {
        onView(withId(R.id.etEmail)).perform(typeText("expressoyahoo.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("exp1100"), closeSoftKeyboard());

        onView(withId(R.id.btn_Signin))
                .perform(click());
        onView(withId(R.id.etEmail)).check(matches(hasErrorText("Enter a valid email")));
//        onView(withText("Enter a valid email")).check(matches(isDisplayed()));

    }


    @Test
    public void empty_passwordAnd_email() {


        onView(withId(R.id.btn_Signin))
                .perform(click());
        onView(withId(R.id.etPassword)).check(matches(hasErrorText("Enter Password")));
        onView(withId(R.id.etEmail)).check(matches(hasErrorText("Enter email")));
//        onView(withText("Enter a valid email")).check(matches(isDisplayed()));

    }

    @Test
    public void empty_password() {
        onView(withId(R.id.etEmail)).perform(typeText("expresso@yahoo.com"), closeSoftKeyboard());


        onView(withId(R.id.btn_Signin))
                .perform(click());
        onView(withId(R.id.etPassword)).check(matches(hasErrorText("Enter Password")));
//        onView(withText("Enter a valid email")).check(matches(isDisplayed()));

    }

    //checking that confirm password takes user to the right page
    @Test
    public void forgotPassword() {


        onView(withId(R.id.forgot_password))
                .perform(click());
        onView(withText("Confirm Reset")).check(matches(isDisplayed()));

    }

    //checking that the reister text takes user to the right page
    @Test
    public void Register() {

        onView(withId(R.id.register_text))
                .perform(click());
        onView(withText("Sign Up")).check(matches(isDisplayed()));

    }

}
