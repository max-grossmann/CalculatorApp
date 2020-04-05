package de.maaxgr.calculatorapp.ui.main

data class MainViewState(val expression: String)


sealed class MainViewEffect {
    data class OperatorCurrentlyNotSupported(val operator: Char): MainViewEffect()
    object CanNotStartWithOperator : MainViewEffect()
}

sealed class MainViewEvent {
    data class NumberInputEvent(val numberChar: Char) : MainViewEvent()
    data class OperatorInputEvent(val operatorChar: Char) : MainViewEvent()
    object ClearInputEvent : MainViewEvent()
    object EvaluateInputEvent : MainViewEvent()
}

