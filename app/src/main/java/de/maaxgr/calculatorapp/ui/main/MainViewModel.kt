package de.maaxgr.calculatorapp.ui.main

import android.app.Application
import de.maaxgr.calculatorapp.ui.api.MviViewModel
import de.maaxgr.calculatorapp.utils.BigNumberCalculator

class MainViewModel(application: Application) : MviViewModel<MainViewState, MainViewEffect, MainViewEvent>(application) {

    init {
        viewState = MainViewState("")
    }

    override fun process(viewEvent: MainViewEvent) {
        super.process(viewEvent)

        when (viewEvent) {
            is MainViewEvent.NumberInputEvent ->  numberInput(viewEvent)
            is MainViewEvent.OperatorInputEvent -> operatorInput(viewEvent)
            is MainViewEvent.ClearInputEvent -> clearInput()
            is MainViewEvent.EvaluateInputEvent -> evaluateInput()
        }
    }

    private fun numberInput(viewEvent: MainViewEvent.NumberInputEvent) {
        viewState = MainViewState(viewState.expression + viewEvent.numberText)
    }

    private fun operatorInput(viewEvent: MainViewEvent.OperatorInputEvent) {
        viewState = MainViewState(viewState.expression + viewEvent.operatorText)
    }

    private fun clearInput() {
        viewState = MainViewState("")
    }

    private fun evaluateInput() {
        val addendos = viewState.expression.split("+")
        val calc = BigNumberCalculator()

        val sum = calc.addStrings(addendos)
        viewState = MainViewState(sum)
    }

}