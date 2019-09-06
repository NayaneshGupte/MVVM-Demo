package com.test.shoppingapp.shoppinglist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.squareup.rx2.idler.Rx2Idler
import com.test.shoppingapp.R
import com.test.shoppingapp.shoppinglist.customaction.RecyclerViewAction
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ShoppingListFragmentTest {

    @get:Rule
    var rule = ActivityScenarioRule(ShoppingListActivity::class.java)


    @Before
    fun register() {
        RxJavaPlugins.setInitComputationSchedulerHandler(
            Rx2Idler.create("RxJava 2.x Computation Scheduler")
        );
        RxJavaPlugins.setInitIoSchedulerHandler(
            Rx2Idler.create("RxJava 2.x IO Scheduler")
        );
    }

    /**
     * Clicks on a row and checks that the activity detected the click.
     */
    @Test
    fun row_Click_4() {
        onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    4,
                    clickOnViewChild(R.id.img_product_pic)
                )
            )
    }


    /**
     * Clicks on a row and checks that the activity detected the click.
     */
    @Test
    fun row_Click_5() {
        onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    clickOnViewChild(R.id.img_product_pic)
                )
            )
    }

    private fun clickOnViewChild(viewToClickInTheRow: Int): ViewAction {

        return RecyclerViewAction.clickOnViewChild(viewToClickInTheRow)
    }
}