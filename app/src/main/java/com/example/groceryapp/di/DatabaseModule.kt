package com.example.groceryapp.di

import android.app.Application
import androidx.room.Room
import com.example.groceryapp.data.local.dao.CartDao
import com.example.groceryapp.utils.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(
            context = app,
            klass = Database::class.java,
            name ="grocery_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCartDao(database: Database): CartDao{
        return database.cartDao
    }
}