package com.example.vinilos

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.not
import org.hamcrest.core.AllOf
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
class ExampleInstrumentedTest {

    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.vinilos", appContext.packageName)
    }

    @Test
    fun checkItemMenuAlbum() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun checkCatalogosAlbumsDisplayed() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .perform(ViewActions.click())
        onView(withId(R.id.albums))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun checkCatalogoAlbumsWithButtonAddDisplayed() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .check(ViewAssertions.matches(isDisplayed()))
            .perform(ViewActions.click())
        onView(withId(R.id.roll_button))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun goToListAlbums() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .perform(ViewActions.click())
    }

    @Test
    fun goToCreateAlbum() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .perform(ViewActions.click())
        onView(withId(R.id.roll_button))
            .perform(ViewActions.click())
        onView(withId(R.id.createAlbum))
            .check(ViewAssertions.matches(isDisplayed()))
    }


    @Test
    fun createAlbum() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .perform(ViewActions.click())
        onView(withId(R.id.roll_button))
            .perform(ViewActions.click())
        onView(withId(R.id.nombreAlbum))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.fechaLanzamiento))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("1982-11-30"))
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.descripcion))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.cover))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"))
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.createButton))
            .perform(ViewActions.click())
        onView(withId(R.id.roll_button))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun createAlbumWithNameEmpty() {
        onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        onView(withId(R.id.nav_album))
            .perform(ViewActions.click())
        onView(withId(R.id.roll_button))
            .perform(ViewActions.click())

        onView(withId(R.id.fechaLanzamiento))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("1982-11-30"))
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.descripcion))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.cover))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"))
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.createButton))
            .perform(ViewActions.click())
        /*onView(withId(R.id.roll_button))
            .check(ViewAssertions.matches(not(isDisplayed())))*/
    }

}