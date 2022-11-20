package com.example.vinilos

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AlbumsDetailTest {
    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun goToDetailAlbum() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .perform(ViewActions.click())
        onView(withId(R.id.albums))
            .check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withText("Prueba album"))
            .perform( ViewActions.click())

        onView(withId(R.id.albumDetail))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun albumDetailWithTitle() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .perform(ViewActions.click())
        onView(withId(R.id.albums))
            .check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withText("Prueba album"))
            .perform( ViewActions.click())

        onView(withId(R.id.txtAlbumName))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withText("Prueba album"))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun albumDetailWithCover() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .perform(ViewActions.click())
        onView(withId(R.id.albums))
            .check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withText("Prueba album"))
            .perform( ViewActions.click())

        onView(withId(R.id.cover))
            .check(ViewAssertions.matches(isDisplayed()))
    }

}