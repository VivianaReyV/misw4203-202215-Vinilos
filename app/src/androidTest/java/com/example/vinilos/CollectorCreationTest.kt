package com.example.vinilos

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class CollectorCreationTest {
    @Rule
    @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Test
    fun goToCreateCollector() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_collector))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.createCollector))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun createCollector() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_collector))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.nombreColeccionista))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("CollectorName"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.telefono))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("3101232323"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.email))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("collector@email.com"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.scrollCreateCollector))
            .perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.createCollectorButton))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun createCollectorWithEmptyName() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_collector))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.telefono))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("3101232323"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.email))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("collector@email.com"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.scrollCreateCollector))
            .perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.createCollectorButton))
            .perform(ViewActions.click())
       Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun createCollectorWithEmptyPhone() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_collector))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.nombreColeccionista))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("CollectorName"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.email))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("collector@email.com"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.scrollCreateCollector))
            .perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.createCollectorButton))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun createCollectorWithEmptyEmail() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_collector))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.nombreColeccionista))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("CollectorName"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.telefono))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("3101232323"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.scrollCreateCollector))
            .perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.createCollectorButton))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun createCollectorWithInvalidEmail() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.navigation_drawer_open))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nav_collector))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.nombreColeccionista))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("CollectorName"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.telefono))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("3101232323"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.email))
            .perform(ViewActions.click(), ViewActions.typeTextIntoFocusedView("collector"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.scrollCreateCollector))
            .perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.createCollectorButton))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.roll_button))
            .check(ViewAssertions.doesNotExist())
    }
}