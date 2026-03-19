package com.example.calculator.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.viewmodel.CalculatorViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
    }

    private fun observeData() {
        viewModel.result.observe(this) {
            binding.result.text = it
        }
    }

    fun numberClick(view: View) {
        val value = (view as Button).text.toString()
        viewModel.onNumberClick(value)
    }

    fun operatorClick(view: View) {
        val value = (view as Button).text.toString()
        viewModel.onOperatorClick(value)
    }

    fun equalClick(view: View) {
        viewModel.onEqual()
    }

    fun clearClick(view: View) {
        viewModel.onClear()
    }

}