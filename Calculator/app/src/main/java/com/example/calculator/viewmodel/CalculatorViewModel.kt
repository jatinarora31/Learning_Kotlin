package com.example.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.repository.CalculatorRepository
class CalculatorViewModel : ViewModel() {

    private val repository = CalculatorRepository()

    private val _result = MutableLiveData("0")
    val result: LiveData<String> = _result

    fun onNumberClick(number: String) {
        if (_result.value == "0") _result.value = number
        else _result.value += number
    }

    fun onOperatorClick(op: String) {
        _result.value += op
    }

    fun onClear() {
        _result.value = "0"
    }

    fun onEqual() {
        try {
            val res = repository.calculate(_result.value ?: "0")
            _result.value = res.toString()
        } catch (e: Exception) {
            _result.value = "ERROR"
        }
    }
}