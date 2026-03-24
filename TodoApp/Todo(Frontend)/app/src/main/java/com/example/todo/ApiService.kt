package com.example.todo

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/todos")
    suspend fun getAllTodos(): Response<List<Todo>>

}