package com.jr.superPlayerAuction.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jr.superPlayerAuction.roomdb.model.TeamEntity

@Database(entities = [TeamEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
}