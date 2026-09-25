package com.example.groceryapp.data.repository

import com.example.groceryapp.domain.model.Product
import com.example.groceryapp.domain.repository.ProductRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProductRepository {
    override fun observeProducts(): Flow<List<Product>> = callbackFlow {
        val listener = firestore.collection("products")
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .limit(50)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val products = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(Product::class.java)?.copy(
                        id = doc.id
                    )
                } ?: emptyList()
                trySend(products)
            }
        awaitClose { listener.remove() }
    }

    override suspend fun toggleFavorite(productId: String, isFavorite: Boolean): Result<Unit> {
        return try {
            firestore.collection("products").document(productId)
                .update("isFavorite", isFavorite)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProduct(productId: String) : Result<Product>{
        return try {
            val doc = firestore.collection("products")
                .document(productId)
                .get()
                .await()
            val product = doc.toObject(Product::class.java)
                ?.copy(id = doc.id)
            if (product != null) {
                Result.success(product)
            } else {
                Result.failure(Exception("Product not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}