package de.maaxgr.calculatorapp.utils

import android.view.View

fun <T : View> Set<T>.setOnClickListener(callback: (View) -> Unit) {
    forEach { view ->
        view.setOnClickListener { callback(it) }
    }
}