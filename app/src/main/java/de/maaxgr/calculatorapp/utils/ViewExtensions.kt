package de.maaxgr.calculatorapp.utils

import android.view.View
import de.maaxgr.calculatorapp.api.MviViewModel

fun <T : View> Set<T>.setOnClickListener(callback: (View) -> Unit) {
    forEach { view ->
        view.setOnClickListener { callback(it) }
    }
}

fun <A, B, C, T : View, V : MviViewModel<A, B, C>> Set<T>.processEventOnClick(vm: MviViewModel<A, B, C>, viewEvent: (View) -> C) {
    setOnClickListener { vm.process(viewEvent(it)) }
}

fun <A, B, C> View.processEventOnClick(vm: MviViewModel<A, B, C>, viewEvent: (View) -> C) {
    setOnClickListener { vm.process(viewEvent(it)) }
}

fun <A, B, C> View.processEventOnClick(vm: MviViewModel<A, B, C>, viewEvent: C) {
    setOnClickListener { vm.process(viewEvent) }
}

val View.tagAsChar: Char
    get() = tag.toString().first()

