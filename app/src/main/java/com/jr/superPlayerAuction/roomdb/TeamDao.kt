package com.jr.superPlayerAuction.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jr.superPlayerAuction.model.Team
import com.jr.superPlayerAuction.roomdb.model.TeamEntity

@Dao
interface TeamDao {

    @Insert
    fun insertTeam(team:TeamEntity)

    @Query("SELECT * FROM Team")
    fun retrieveTeamList():List<TeamEntity>
}