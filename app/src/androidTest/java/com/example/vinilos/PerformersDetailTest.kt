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
class PerformersDetailTest {
    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun goToDetailPerformer() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_performer))
            .perform(ViewActions.click())
        onView(withId(R.id.performers))
            .check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withText("Shakira"))
            .perform( ViewActions.click())

        onView(withId(R.id.performerDetail))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun performerDetailWithTitle() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_performer))
            .perform(ViewActions.click())
        onView(withId(R.id.performers))
            .check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withText("Shakira"))
            .perform( ViewActions.click())

        onView(withId(R.id.txtPerformerName))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withText("Shakira"))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.txtDescription))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun performerDetailWithCover() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_performer))
            .perform(ViewActions.click())
        onView(withId(R.id.performers))
            .check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withText("Shakira"))
            .perform( ViewActions.click())

        onView(withId(R.id.cover))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}