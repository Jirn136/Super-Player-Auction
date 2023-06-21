package com.jr.superPlayerAuction.roomdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Team")
data class TeamEntity(
    @PrimaryKey(autoGenerate = true) val id :Int = 0,
    val teamName: String
)
