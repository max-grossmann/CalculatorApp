package de.maaxgr.calculatorapp.ui.main

import android.app.Application
import de.maaxgr.calculatorapp.api.MviViewModel
import de.maaxgr.calculatorapp.utils.BigNumberCalculator
import de.maaxgr.calculatorapp.utils.containsNot

class MainViewModel(application: Application) : MviViewModel<MainViewState, MainViewEffect, MainViewEvent>(application) {

    companion object Constants {
        val SUPPORTED_OPERATORS = setOf('+')
        val OPERATORS = setOf('*', '/', '+', '-')
    }

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
        viewState = MainViewState(viewState.expression + viewEvent.numberChar)
    }

    private fun operatorInput(event: MainViewEvent.OperatorInputEvent) {
        if (SUPPORTED_OPERATORS.containsNot(event.operatorChar)) {
            viewEffect = MainViewEffect.OperatorCurrentlyNotSupported(event.operatorChar)
            return
        }

        val lastChar = viewState.expression.lastOrNull()

        if (lastChar == null) {
            viewEffect = MainViewEffect.CanNotStartWithOperator
            return
        }

        var newExpression = viewState.expression

        //tmp remove last character if already a operator => only one operator should be set
        if (OPERATORS.contains(lastChar)) {
            newExpression = newExpression.dropLast(1)
        }

        newExpression += event.operatorChar

        viewState = MainViewState(newExpression)
    }

    private fun clearInput() {
        viewState = MainViewState("")
    }

    private fun evaluateInput() {
        val addends = viewState.expression.split("+")
        val calc = BigNumberCalculator()

        val sum = calc.addStrings(addends)
        viewState = MainViewState(sum)
    }

}