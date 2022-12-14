package com.example.vinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CollectorListTest {
    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkItemMenuCollector() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_collector))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun checkCatalogosCollectorsDisplayed() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_collector))
            .perform(ViewActions.click())
        onView(withId(R.id.collectors))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun checkCatalogoCollectorsWithButtonAddDisplayed() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_collector))
            .check(ViewAssertions.matches(isDisplayed()))
            .perform(ViewActions.click())
        onView(withId(R.id.roll_button))
            .check(ViewAssertions.matches(isDisplayed()))
    }

}