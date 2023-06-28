package com.jr.superPlayerAuction.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jr.superPlayerAuction.roomdb.model.PlayerEntity
import com.jr.superPlayerAuction.roomdb.model.TeamEntity
import com.jr.superPlayerAuction.utils.Constants.DATABASE_VERSION

@Database(entities = [TeamEntity::class, PlayerEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao

    abstract fun playerDao(): PlayerDao
}