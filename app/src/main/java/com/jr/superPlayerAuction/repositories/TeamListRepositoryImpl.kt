package com.jr.superPlayerAuction.repositories

import com.jr.superPlayerAuction.model.Team
import com.jr.superPlayerAuction.roomdb.TeamDao
import com.jr.superPlayerAuction.utils.convertToModel
import javax.inject.Inject

class TeamListRepositoryImpl @Inject constructor(private val teamDao: TeamDao) :
    TeamListRepository {
    override suspend fun retrieveTeamList(): ArrayList<Team> {
        val teamList = teamDao.retrieveTeamList()
        return teamList.convertToModel()
    }
}
