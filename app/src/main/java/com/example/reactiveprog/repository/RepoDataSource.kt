package com.example.reactiveprog.repository

import com.example.reactiveprog.db.Repo
import io.reactivex.Observable

interface RepoDataSource {

    fun fetchRepos(username:String) : Observable<List<Repo>>

    fun saveRepos(repos: List<Repo>)
}