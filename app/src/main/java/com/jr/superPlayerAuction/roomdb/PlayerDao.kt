package com.jr.superPlayerAuction.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jr.superPlayerAuction.roomdb.model.PlayerEntity

@Dao
interface PlayerDao {

    @Insert
    fun insertPlayer(player: PlayerEntity)

    @Query("SELECT * FROM Player where teamName like (:tName) ")
    fun retrievePlayerList(tName: String):List<PlayerEntity>

    @Update
    fun updatePlayer(player: PlayerEntity)
}