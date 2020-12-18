package com.oo115.myapplication;


import android.content.Intent;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import androidx.test.espresso.Root;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

//    @Rule
//    public FragmentTestRule<HomeFragment> mFragmentTestRule = new FragmentTestRule<>(HomeFragment.class);

    private Random rand = new Random();

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Rule
    public FragmentTestRule<RegisterFragment> mFragmentTestRule = new FragmentTestRule<>(RegisterFragment.class);


    @Before
    public void setUp() throws Exception {
        Intents.init();


    }

    @After
    public void cleanUp() {
        Intents.release();
    }

    @Test
    public void fragment_can_be_instantiated() {

        // Launch the activity to make the fragment visible
//        mFragmentTestRule.launchActivity(null);
        onView(withText("Quick Workout")).check(matches(isDisplayed()));
//
        onView(withId(R.id.btnQuickWork)).check(matches(isDisplayed()));

    }

//page greeting unit test

    @Test
    public void pageGreeting_test() {

        HomeFragment homeFragment = new HomeFragment();

        String greeting_txt = homeFragment.greeting(0, "Paul");

        assertEquals("Good Morning Paul", greeting_txt);

    }




    //
//
//    /*
//   testing when you click the weight nav in the drawer
//    */
    @Test
    public void drawer_weightNav_test() {

        mActivityRule.launchActivity(new Intent());
        onView(withId(R.id.btnQuickWork)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout))
                .perform(click());


        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_weight));
        onView(withText("Body Composition")).check(matches(isDisplayed()));

    }
//


    public static Matcher<Root> isPopupWindow() {
        return isPlatformPopup();
    }

    /*
    This test registers a new user and tests the changing email feature
     */

    @Test
    public void change_email_test() throws InterruptedException {

        String a_z = "abcdefghijklmnopqrst";
        String email = generateString(rand, a_z, 6) + "@yahoo.com";


        mFragmentTestRule.launchActivity(null);

        String email_change = generateString(rand, a_z, 6) + "@yahoo.com";
        String password = "123456";


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


        Thread.sleep(2000);
        onView(withText("OK")).perform(click());
        Thread.sleep(1000);
        openContextualActionModeOverflowMenu();
        onView(withText(R.string.settings))
                .perform(click());
        onView(withId(R.id.settings_listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.settings_listView)).atPosition(0).perform(click());
        onView(withText(R.string.change_email)).check(matches(isDisplayed()));
        onView(withId(R.id.email_resetET)).perform(typeText(email_change), closeSoftKeyboard());
        onView(withId(R.id.emailConfirm_resetET2)).perform(typeText(email_change), closeSoftKeyboard());

        onView(withId(R.id.changeEmail_btn)).perform(click());

        onView(withText(R.string.str_ok)).perform(click());

        onView(withId(R.id.current_email_restET)).check(matches(withText(email_change)));


        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withText(R.string.settings)).check(matches(isDisplayed()));
        onView(isRoot()).perform(ViewActions.pressBack());
        openContextualActionModeOverflowMenu();
        onView(withText(R.string.menu_log_out))
                .perform(click());
        onView(withText(R.string.menu_log_out)).perform(click());

        Thread.sleep(2000);
        onView(withId(R.id.btn_letsGetIt)).perform(click());

        Thread.sleep(2000);
        onView(withId(R.id.etEmail)).perform(typeText(email_change), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("123456"), closeSoftKeyboard());

        onView(withId(R.id.btn_Signin))
                .perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.btnQuickWork)).check(matches(isDisplayed()));

    }



    @Test
    public void delete_user_test() throws InterruptedException {
        mFragmentTestRule.launchActivity(null);

        Random rand = new Random();
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


//        onView(withText(R.string.registration_success)).inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//                .perform(click());


//        onView(withText(R.string.registration_success))
//    .inRoot(isDialog()) // <---
//                .check(matches(isDisplayed()))
//                .perform(click());
        Thread.sleep(2000);
        onView(withText("OK")).perform(click());
        openContextualActionModeOverflowMenu();

        onView(withText(R.string.settings))
                .perform(click());
        onView(withText("Delete Account")).perform(click());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.etEmail)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.btn_Signin))
                .perform(click());

        onView(withText("Invalid user")).check(matches(isDisplayed()));


    }

    @Test
    public void userGuide_test() {
        mActivityRule.launchActivity(new Intent());

        openContextualActionModeOverflowMenu();

        onView(withText(R.string.settings))
                .perform(click());

        onView(withText("Getting Started")).perform(click());//gettingStarted_listView

        onView(withId(R.id.gettingStarted_listView)).check(matches(isDisplayed()));

    }


    public String generateString(Random random, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }


}
