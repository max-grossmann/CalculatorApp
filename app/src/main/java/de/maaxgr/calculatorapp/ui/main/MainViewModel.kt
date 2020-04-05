package de.maaxgr.calculatorapp.ui.main

import android.app.Application
import de.maaxgr.calculatorapp.api.MviViewModel
import de.maaxgr.calculatorapp.utils.BigNumberCalculator
import de.maaxgr.calculatorapp.utils.containsNot
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : MviViewModel<MainViewState, MainViewEffect, MainViewEvent>(application) {

    companion object Constants {
        val SUPPORTED_OPERATORS = setOf('+')
        val OPERATORS = setOf('*', '/', '+', '-')
    }

    init {
        initState()
    }

    override fun process(viewEvent: MainViewEvent) {
        super.process(viewEvent)

        when (viewEvent) {
            is MainViewEvent.NumberInputEvent ->  numberInput(viewEvent)
            is MainViewEvent.OperatorInputEvent -> operatorInput(viewEvent)
            is MainViewEvent.ClearInputEvent -> initState()
            is MainViewEvent.EvaluateInputEvent -> evaluateInput(true)
        }
    }

    private fun numberInput(viewEvent: MainViewEvent.NumberInputEvent) {
        val number = if (viewState.overrideNumber) {
            viewEvent.numberChar.toString()
        } else {
            viewState.currentNumber + viewEvent.numberChar
        }

        viewState = viewState.copy(
            overrideNumber = false,
            currentNumber = number,
            visibleNumber = number
        )
    }

    private fun operatorInput(event: MainViewEvent.OperatorInputEvent) {
        if (SUPPORTED_OPERATORS.containsNot(event.operatorChar)) {
            viewEffect = MainViewEffect.OperatorCurrentlyNotSupported(event.operatorChar)
            return
        }

        if (viewState.cachedOperator != null) {
            evaluateInput(false)
        }

        viewState = viewState.copy(
            overrideNumber = true,
            cachedOperator = event.operatorChar,
            cachedNumber = viewState.visibleNumber
        )
    }

    private fun initState() {
        viewState = MainViewState(visibleNumber = "0")
    }

    private fun evaluateInput(byEqualButton: Boolean) {
        val calc = BigNumberCalculator()

        viewState.cachedOperator?.let {

            val result = when (viewState.cachedOperator) {
                '+' -> calc.addStrings(listOf(viewState.cachedNumber, viewState.currentNumber))
                else -> throw IllegalArgumentException("Not supported operator")
            }

            viewState = viewState.copy(
                overrideNumber = true,
                cachedOperator = if (byEqualButton) { null } else { viewState.cachedOperator },
                cachedNumber = result,
                visibleNumber = result
            )

        }


    }

}