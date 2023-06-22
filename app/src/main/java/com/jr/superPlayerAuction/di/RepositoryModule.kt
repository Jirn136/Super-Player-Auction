package com.jr.superPlayerAuction.di

import com.jr.superPlayerAuction.repositories.PlayerListRepositoryImpl
import com.jr.superPlayerAuction.repositories.TeamListRepositoryImpl
import com.jr.superPlayerAuction.repositories.interfaces.PlayerListRepository
import com.jr.superPlayerAuction.repositories.interfaces.TeamListRepository
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
    abstract fun bindTeamListRepository(repositoryImpl: TeamListRepositoryImpl): TeamListRepository

    @Binds
    @Singleton
    abstract fun bindPlayerListRepository(repositoryImpl: PlayerListRepositoryImpl): PlayerListRepository
}