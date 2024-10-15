package com.example.newscompose.data.di

import android.app.Application
import com.example.newscompose.data.manager.LocalUserManagerImpl
import com.example.newscompose.domain.manager.LocalUserManager
import com.example.newscompose.domain.usecases.AppEntryUseCases
import com.example.newscompose.domain.usecases.ReadAppEntry
import com.example.newscompose.domain.usecases.SaveAppEntry
import com.example.newscompose.network.RetrofitInstance
import com.example.newscompose.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )




    @Provides
    @Singleton
    fun provideNewsRepository(): NewsRepository {
        return NewsRepository(RetrofitInstance.api)
    }

}