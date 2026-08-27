package com.example.groceryapp.data.repository

import com.example.groceryapp.domain.model.User
import com.example.groceryapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    override suspend fun login(email: String, password: String): Result<User?> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user?.toDomainUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        phone: String
    ): Result<User?> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(result.user?.toDomainUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUser(): User?{
        return firebaseAuth.currentUser?.toDomainUser()
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    private fun FirebaseUser.toDomainUser(): User{
        return User(
            id = this.uid,
            email = this.email,
            phoneNumber = this.phoneNumber
        )
    }
}