package de.maaxgr.calculatorapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import de.maaxgr.architecturecompoenentutilities.activityBinding
import de.maaxgr.architecturecompoenentutilities.enable
import de.maaxgr.calculatorapp.R
import de.maaxgr.calculatorapp.databinding.ActivityMainBinding
import de.maaxgr.calculatorapp.api.MviActivity
import de.maaxgr.calculatorapp.utils.processEventOnClick
import de.maaxgr.calculatorapp.utils.tagAsChar

class MainActivity : MviActivity<MainViewState, MainViewEffect, MainViewEvent, MainViewModel>() {


    override val vm: MainViewModel by viewModels()
    private val b: ActivityMainBinding by activityBinding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b.enable(this)

        with(b) {
            setOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
                .processEventOnClick(vm) {
                    MainViewEvent.NumberInputEvent(it.tagAsChar)
                }

            setOf(btnPlus, btnMinus, btnMultiply, btnDivide)
                .processEventOnClick(vm) {
                    MainViewEvent.OperatorInputEvent(it.tagAsChar)
                }

            btnClear.processEventOnClick(vm, MainViewEvent.ClearInputEvent)
            btnSubmit.processEventOnClick(vm, MainViewEvent.EvaluateInputEvent)
        }
    }

    override fun renderViewState(viewState: MainViewState) = with(b) {
        tvInput.text = viewState.visibleNumber
    }

    override fun renderViewEffect(viewEffect: MainViewEffect) {
        when (viewEffect) {
            is MainViewEffect.OperatorCurrentlyNotSupported -> {
                Toast.makeText(
                    this,
                    "This operator is currently not supported",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is MainViewEffect.CanNotStartWithOperator -> {
                Toast.makeText(
                    this,
                    "First symbol has to be a number!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
