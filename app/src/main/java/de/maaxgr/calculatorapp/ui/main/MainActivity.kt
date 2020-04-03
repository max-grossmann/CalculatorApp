package de.maaxgr.calculatorapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import de.maaxgr.architecturecompoenentutilities.activityBinding
import de.maaxgr.architecturecompoenentutilities.enable
import de.maaxgr.calculatorapp.R
import de.maaxgr.calculatorapp.databinding.ActivityMainBinding
import de.maaxgr.calculatorapp.ui.api.MviActivity
import de.maaxgr.calculatorapp.utils.setOnClickListener

class MainActivity : MviActivity<MainViewState, MainViewEffect, MainViewEvent, MainViewModel>() {


    override val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by activityBinding<ActivityMainBinding>(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.enable(this)

        with(binding) {
            setOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
                .setOnClickListener { viewModel.process(MainViewEvent.NumberInputEvent(it.tag as String)) }

            setOf(btnPlus, btnMinus, btnMultiply, btnDivide)
                .setOnClickListener { viewModel.process(MainViewEvent.OperatorInputEvent(it.tag as String)) }

            btnClear.setOnClickListener { viewModel.process(MainViewEvent.ClearInputEvent) }
            btnSubmit.setOnClickListener { viewModel.process(MainViewEvent.EvaluateInputEvent) }
        }

    }

    override fun renderViewState(viewState: MainViewState) {
        binding.tvInput.text = viewState.expression
    }

    override fun renderViewEffect(viewEffect: MainViewEffect) {

    }
}
