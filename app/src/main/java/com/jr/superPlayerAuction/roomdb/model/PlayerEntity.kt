package com.jr.superPlayerAuction.roomdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Player")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val playerName: String,
    val age: Int,
    val contactNumber:String,
    val speciality: Int,
    val amount: String,
    val batType: Int,
    val bowlType: Int,
    val teamName: String,
    val playerProfile: String,
)