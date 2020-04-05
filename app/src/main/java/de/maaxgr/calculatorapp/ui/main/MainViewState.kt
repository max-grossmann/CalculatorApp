package de.maaxgr.calculatorapp.ui.main

data class MainViewState(
    val visibleNumber: String = "",
    val cachedNumber: String = "",
    val currentNumber: String = "",
    val cachedOperator: Char? = null,
    val overrideNumber: Boolean = false
)

sealed class MainViewEffect {
    data class OperatorCurrentlyNotSupported(val operator: Char): MainViewEffect()
}

sealed class MainViewEvent {
    data class NumberInputEvent(val numberChar: Char) : MainViewEvent()
    data class OperatorInputEvent(val operatorChar: Char) : MainViewEvent()
    object ClearInputEvent : MainViewEvent()
    object EvaluateInputEvent : MainViewEvent()
}

