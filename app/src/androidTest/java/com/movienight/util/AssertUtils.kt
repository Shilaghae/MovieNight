package com.movienight.util

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.movienight.util.RecyclerViewMatcher.Companion.matchesWithTheList

class AssertUtils {
    companion object {
        fun assertRecyclerViewItemsMatchList(@IdRes recyclerViewId: Int,
                @IdRes recyclerViewItemId: Int,
                list: List<String>) {
            onView(withId(recyclerViewId)).check(matches(matchesWithTheList(recyclerViewItemId, list)))
        }
    }
}