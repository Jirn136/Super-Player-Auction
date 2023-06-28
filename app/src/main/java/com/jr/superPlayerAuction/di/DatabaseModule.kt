package com.jr.superPlayerAuction.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jr.superPlayerAuction.roomdb.AppDatabase
import com.jr.superPlayerAuction.roomdb.PlayerDao
import com.jr.superPlayerAuction.roomdb.TeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideMigration1to2(): Migration {
        return object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.apply {
                    execSQL(
                        "CREATE TABLE IF NOT EXISTS Player_new (id INTEGER PRIMARY KEY NOT NULL," +
                                "playerName TEXT NOT NULL, " +
                                "age INTEGER NOT NULL," +
                                "speciality INTEGER NOT NULL," +
                                "amount TEXT NOT NULL," +
                                "batType INTEGER NOT NULL, " +
                                "bowlType INTEGER NOT NULL," +
                                "teamName TEXT NOT NULL)"
                    )
                    execSQL(
                        "INSERT INTO Player_new (id," +
                                "playerName,age," +
                                "speciality,amount," +
                                "batType,bowlType,teamName) " +
                                "SELECT id," +
                                "playerName,age," +
                                "speciality,amount," +
                                "batType,bowlType,teamName FROM Player"
                    )
                    execSQL("DROP TABLE Player")
                    execSQL("ALTER TABLE Player_new RENAME TO Player")
                }
            }

        }
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        migration: Migration,
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "PlayerAuction")
            .addMigrations(migration)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

    @Provides
    fun provideTeamDao(appDatabase: AppDatabase): TeamDao = appDatabase.teamDao()

    @Provides
    fun providePlayerDao(appDatabase: AppDatabase): PlayerDao = appDatabase.playerDao()
}