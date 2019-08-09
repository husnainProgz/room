package com.example.reactiveprog.repository

import com.example.reactiveprog.db.Repo
import com.example.reactiveprog.network.GithubClient
import io.reactivex.Observable

//A Kotlin object is like a class that can't be instantiated so it must be called by name. (a static class per se)

object RepoRemoteSource : RepoDataSource{
    override fun fetchRepos(username: String): Observable<List<Repo>> {
        return GithubClient.getGithubSerivce().getStarredRepos(username)
    }

    override fun saveRepos(repos: List<Repo>) {
    }
}