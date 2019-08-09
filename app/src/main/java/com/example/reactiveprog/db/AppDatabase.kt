package com.example.reactiveprog.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Repo::class),version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepoDao() : RepoDAO

    companion object{
        var INSTANCE :AppDatabase? = null

        fun getInstance(context: Context):AppDatabase?{

            INSTANCE?.let { INSTANCE }?: run {
                INSTANCE = Room.databaseBuilder(context,AppDatabase::class.java,"repo.db")
                    .build()
            }
            return INSTANCE
        }
    }
}