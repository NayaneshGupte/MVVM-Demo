package com.test.shoppingapp.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection

/**
 * Marks an activity / fragment injectable.
 */
interface Injectable

/**
 * Kotlin extension functions
 */
fun Application.applyAutoInjector() = registerActivityLifecycleCallbacks(
    object : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            handleActivity(activity)
        }

        override fun onActivityStarted(activity: Activity) {

        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

        }

        override fun onActivityDestroyed(activity: Activity) {

        }
    })

private fun handleActivity(activity: Activity) {
    if (activity is Injectable || activity is HasAndroidInjector) {
        AndroidInjection.inject(activity)
    }
    if (activity is androidx.fragment.app.FragmentActivity) {
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
            object : androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(fm: androidx.fragment.app.FragmentManager, f: androidx.fragment.app.Fragment, s: Bundle?) {
                    if (f is Injectable) {
                        AndroidSupportInjection.inject(f)
                    }
                }
            }, true
        )
    }
}

