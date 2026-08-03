package com.example.groceryapp.di

import com.example.groceryapp.data.repository.AuthRepositoryImpl
import com.example.groceryapp.data.repository.OnboardingRepositoryImpl
import com.example.groceryapp.domain.repository.AuthRepository
import com.example.groceryapp.domain.repository.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideOnboardingRepository(onboardingRepositoryImpl: OnboardingRepositoryImpl): OnboardingRepository

    @Binds
    @Singleton
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
