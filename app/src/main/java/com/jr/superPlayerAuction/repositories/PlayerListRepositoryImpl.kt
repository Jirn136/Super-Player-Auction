package com.jr.superPlayerAuction.repositories

import com.jr.superPlayerAuction.model.Player
import com.jr.superPlayerAuction.repositories.interfaces.PlayerListRepository
import com.jr.superPlayerAuction.roomdb.PlayerDao
import com.jr.superPlayerAuction.utils.convertPlayerEntityToModel
import javax.inject.Inject

class PlayerListRepositoryImpl @Inject constructor(private val playerDao: PlayerDao) :
    PlayerListRepository {

    override suspend fun retrievePlayersList(teamName: String): ArrayList<Player> {
        val playerList = playerDao.retrievePlayerList(teamName)
        return playerList.convertPlayerEntityToModel()
    }
}