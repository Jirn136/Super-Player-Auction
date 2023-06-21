package com.jr.superPlayerAuction.di

import android.content.Context
import androidx.room.Room
import com.jr.superPlayerAuction.roomdb.AppDatabase
import com.jr.superPlayerAuction.roomdb.TeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "PlayerAuction")
            .allowMainThreadQueries().build()

    @Provides
    fun provideTeamDao(appDatabase: AppDatabase): TeamDao = appDatabase.teamDao()
}