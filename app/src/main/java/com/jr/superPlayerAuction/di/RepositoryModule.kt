package com.jr.superPlayerAuction.di

import com.jr.superPlayerAuction.repositories.TeamListRepository
import com.jr.superPlayerAuction.repositories.TeamListRepositoryImpl
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
    abstract fun bindTeamListRepository(repositoryImpl: TeamListRepositoryImpl):TeamListRepository
}