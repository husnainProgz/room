package com.example.reactiveprog.repository

import com.example.reactiveprog.RxApp
import com.example.reactiveprog.db.AppDatabase
import com.example.reactiveprog.db.Repo
import io.reactivex.Observable

//A Kotlin object is like a class that can't be instantiated so it must be called by name. (a static class per se)
object RepoLocalSource : RepoDataSource{

    override fun fetchRepos(username: String): Observable<List<Repo>> {

        //Fom Callable emits single item unlike fromCreate which emits multiple items
        return Observable.fromCallable{
            AppDatabase.getInstance(RxApp.INSTANCE)?.getRepoDao()!!.fetchAllStarRepos()

        }
    }

    override fun saveRepos(repos : List<Repo>) {

         AppDatabase.getInstance(RxApp.INSTANCE)?.getRepoDao()!!.saveAllMyStarRepos(repos)
    }
}