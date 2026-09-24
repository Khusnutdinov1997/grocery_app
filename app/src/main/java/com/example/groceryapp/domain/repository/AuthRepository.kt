package com.example.groceryapp.domain.repository

import com.example.groceryapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User?>
    suspend fun signUp(email: String, password: String, phone: String): Result<User?>
    fun getCurrentUser(): User?
    fun logout()
    fun isAuthenticated(): Flow<Boolean>
}