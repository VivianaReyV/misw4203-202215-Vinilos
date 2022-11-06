package com.example.vinilos

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class AlbumCreationTest {
    @Rule
    @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Test
    fun goToCreateAlbum() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_album))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.createAlbum))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun createAlbum() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_album))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nombreAlbum))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.fechaLanzamiento))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("1982-11-30"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.descripcion))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.cover))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.createButton))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun createAlbumWithEmptyName() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_album))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.fechaLanzamiento))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("1982-11-30"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.descripcion))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.cover))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.createButton))
            .perform(ViewActions.click())
       Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun createAlbumWithEmptyDescription() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_album))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nombreAlbum))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.fechaLanzamiento))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("1982-11-30"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.cover))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.createButton))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun createAlbumWithEmptyDate() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_album))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nombreAlbum))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.descripcion))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.cover))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.createButton))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun createAlbumWithInvalidDate() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_album))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nombreAlbum))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.fechaLanzamiento))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("30-12-1995"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.descripcion))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.cover))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.createButton))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun createAlbumWithEmptyCover() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_album))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nombreAlbum))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.fechaLanzamiento))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("1982-11-30"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.descripcion))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("Thriller"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.createButton))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.doesNotExist())
    }
}