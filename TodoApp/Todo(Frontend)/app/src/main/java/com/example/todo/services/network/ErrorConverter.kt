package com.example.todo.services.network

import com.example.todo.ApiService
import com.example.todo.RetrofitInstance
import com.example.todo.models.GeneralError
import com.google.gson.Gson
import retrofit2.Response

object ErrorConverter {
    fun <T> parseError(response: Response<T>): GeneralError? {
        try {
            return Gson().fromJson(response.errorBody()?.charStream(), GeneralError::class.java)
        } catch (e: Exception) {
            return null
        }
    }
}