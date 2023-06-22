package com.jr.superPlayerAuction.repositories.interfaces

import com.jr.superPlayerAuction.model.Player

interface PlayerListRepository {

    suspend fun retrievePlayersList(teamName:String):ArrayList<Player>
}