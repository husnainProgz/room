package com.example.reactiveprog.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepoDAO {

    @Query(" SELECT * FROM repo")
    fun fetchAllStarRepos() : List<Repo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMyStarRepos(repos : List<Repo>)
}