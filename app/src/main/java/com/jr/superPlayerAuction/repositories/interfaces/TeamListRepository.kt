package com.jr.superPlayerAuction.repositories.interfaces

import com.jr.superPlayerAuction.model.Team
import javax.inject.Singleton

@Singleton
interface TeamListRepository {
    suspend fun retrieveTeamList(): ArrayList<Team>

    suspend fun insertTeam(team: Team): Boolean
}