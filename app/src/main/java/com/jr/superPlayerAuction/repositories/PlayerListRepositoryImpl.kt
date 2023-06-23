package com.jr.superPlayerAuction.repositories

import com.jr.superPlayerAuction.model.Player
import com.jr.superPlayerAuction.repositories.interfaces.PlayerListRepository
import com.jr.superPlayerAuction.roomdb.PlayerDao
import com.jr.superPlayerAuction.utils.convertPlayerEntityToModel
import com.jr.superPlayerAuction.utils.convertToPlayerEntity
import javax.inject.Inject

class PlayerListRepositoryImpl @Inject constructor(private val playerDao: PlayerDao) :
    PlayerListRepository {

    override suspend fun retrievePlayersList(teamName: String): ArrayList<Player> {
        val playerList = playerDao.retrievePlayerList(teamName)
        return playerList.convertPlayerEntityToModel()
    }

    override suspend fun insertPlayer(player: Player): Boolean {
        return try {
            playerDao.insertPlayer(player.convertToPlayerEntity())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}