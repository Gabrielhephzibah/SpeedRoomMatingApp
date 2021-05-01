package com.cherish.speedroommatingapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.runner.RunWith
import com.cherish.speedroommatingapp.ui.MainActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.Before




@RunWith(AndroidJUnit4::class)
class SpeedRoomMatingUITest {


    @get:Rule
    var mActivityRule = ActivityTestRule(
        MainActivity::class.java
    )

    @Before
    fun setUp() {
    }

    @Test
    @Throws(Exception::class)
    fun checkViewPagerIsVisible() {
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))

        }

        @Test
        @Throws(Exception::class)
        fun checkTabLayoutIsVisible() {
            onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))
        }


        @Test
        @Throws(Exception::class)
        fun checkAppBarIsVisible() {
            onView(withId(R.id.appBar)).check(matches(isDisplayed()))
        }


        @Test
        @Throws(Exception::class)
        fun checkToolBarIsVisible() {
            onView(withId(R.id.toolBar)).check(matches(isDisplayed()))
        }

        @Test
        @Throws(Exception::class)
        fun checkStateLayoutIsNotVisible() {
            onView(withId(R.id.stateLayout)).check(matches(not(isDisplayed())))
        }

        @Test
        @Throws(Exception::class)
        fun checkViewPagerFragmentIsDisplayed() {
            onView(allOf(withId(R.id.viewPager), isCompletelyDisplayed())).check(
                matches(
                    withEffectiveVisibility(Visibility.VISIBLE)
                )
            )

        }

        @Test
        @Throws(Exception::class)
        fun checkRecyclerClick() {
            onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
            onView(allOf(withId(R.id.recyclerView), isCompletelyDisplayed())).perform(click())

        }

    @Test
    @Throws(Exception::class)
    fun checkRetryClick() {
        onView(withId(R.id.retry)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.retry), isDisplayed())).perform(click())

    }



}

