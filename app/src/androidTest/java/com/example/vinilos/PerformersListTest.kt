package com.example.vinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PerformersListTest {
    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkItemMenuPerformer() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_artist))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun checkCatalogosPerformersDisplayed() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_artist))
            .perform(ViewActions.click())
        onView(withId(R.id.performers))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun checkCatalogoPerformersWithButtonAddDisplayed() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_artist))
            .check(ViewAssertions.matches(isDisplayed()))
            .perform(ViewActions.click())
        onView(withId(R.id.roll_button))
            .check(ViewAssertions.matches(isDisplayed()))
    }

}