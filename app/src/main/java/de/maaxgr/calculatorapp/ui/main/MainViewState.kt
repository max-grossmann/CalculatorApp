package de.maaxgr.calculatorapp.ui.main

data class MainViewState(val expression: String)


sealed class MainViewEffect {

}

sealed class MainViewEvent {
    data class NumberInputEvent(val numberText: String) : MainViewEvent()
    data class OperatorInputEvent(val operatorText: String) : MainViewEvent()
    object ClearInputEvent : MainViewEvent()
    object EvaluateInputEvent : MainViewEvent()
}

