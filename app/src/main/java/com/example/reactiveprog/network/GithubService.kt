package com.example.reactiveprog.network

import com.example.reactiveprog.db.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.collections.ArrayList

interface GithubService {

    @GET("users/{user}/starred")
    fun getStarredRepos(@Path("user") username:String): Observable<List<Repo>>
}