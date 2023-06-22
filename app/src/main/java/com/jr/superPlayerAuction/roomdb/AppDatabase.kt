package com.jr.superPlayerAuction.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jr.superPlayerAuction.roomdb.model.PlayerEntity
import com.jr.superPlayerAuction.roomdb.model.TeamEntity

@Database(entities = [TeamEntity::class, PlayerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao

    abstract fun playerDao(): PlayerDao
}