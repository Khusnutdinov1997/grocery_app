package com.example.groceryapp.domain.repository

import com.example.groceryapp.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User?>
    suspend fun signUp(email: String, password: String, phone: String): Result<User?>
    fun logout()
}