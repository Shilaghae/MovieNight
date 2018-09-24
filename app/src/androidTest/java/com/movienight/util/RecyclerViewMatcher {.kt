package com.movienight.util

import android.support.annotation.IdRes
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import android.text.TextUtils.isEmpty

class RecyclerViewMatcher {


    companion object {

        fun matchesWithTheList(@IdRes idView: Int, listItem: List<String>): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("The item $idView should be present in the list")
                }

                public override fun matchesSafely(recyclerView: RecyclerView): Boolean {

                    val adapter = recyclerView.adapter
                    val totalItems = adapter.itemCount
                    if (totalItems != listItem.size) {
                        return false
                    }

                    for (i in 0 until totalItems) {
                        val viewHolder = recyclerView.findViewHolderForAdapterPosition(i)
                        val viewById = viewHolder.itemView.findViewById<View>(idView) as? TextView ?: return false

                        val itemText = viewById.text
                        if (isEmpty(itemText) || !listItem.contains(itemText.toString())) {
                            return false
                        }
                    }
                    return true
                }
            }
        }
    }
}