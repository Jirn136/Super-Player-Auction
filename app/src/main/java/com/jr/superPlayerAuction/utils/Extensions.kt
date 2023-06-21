package com.jr.superPlayerAuction.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun showViews(vararg view: View) = view.map { it.show() }

fun goneViews(vararg view: View) = view.map { it.gone() }

val Any.TAG: String
    get() {
        return if (!javaClass.isAnonymousClass) {
            val name = javaClass.simpleName
            if (name.length <= 23) name else name.substring(0, 23)// first 23 chars
        } else {
            val name = javaClass.name
            if (name.length <= 23) name else name.substring(
                name.length - 23,
                name.length
            )// last 23 chars
        }
    }

fun String.toToast(context: Context) = Toast.makeText(context, this, Toast.LENGTH_SHORT).show()

fun getScreenWidth(activity: Activity): Int {
    val displayMetrics = DisplayMetrics()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val display = activity.display
        display?.getRealMetrics(displayMetrics)
    } else {
        @Suppress("DEPRECATION")
        val display = activity.windowManager.defaultDisplay
        @Suppress("DEPRECATION")
        display.getMetrics(displayMetrics)
    }
    return displayMetrics.widthPixels
}

fun getScreenHeight(activity: Activity): Int {
    val displayMetrics = DisplayMetrics()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val display = activity.display
        display?.getRealMetrics(displayMetrics)
    } else {
        @Suppress("DEPRECATION")
        val display = activity.windowManager.defaultDisplay
        @Suppress("DEPRECATION")
        display.getMetrics(displayMetrics)
    }
    return displayMetrics.heightPixels
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)


inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {},
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}
