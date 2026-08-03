package com.example.groceryapp.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<FirebaseUser?>
    suspend fun signUp(email: String, password: String, phone: String): Result<FirebaseUser?>
    fun logout()
}