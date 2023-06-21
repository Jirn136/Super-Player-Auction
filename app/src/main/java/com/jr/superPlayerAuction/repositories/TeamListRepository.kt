package com.jr.superPlayerAuction.repositories

import com.jr.superPlayerAuction.model.Team
import javax.inject.Singleton

@Singleton
interface TeamListRepository {
    suspend fun retrieveTeamList(): ArrayList<Team>

    suspend fun insertTeam(team: Team): Boolean
}