package com.example.reactiveprog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reactiveprog.db.Repo
import com.example.reactiveprog.network.GithubClient
import com.example.reactiveprog.repository.RepoLocalSource
import com.example.reactiveprog.repository.RepoRemoteSource
import com.example.reactiveprog.repository.RepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class RepoViewModel  : ViewModel() {

    /* Composite Disposeable
    * It can hold multiple dispoble and it is very easy to dispose of the single var rather than disposing each and every disposable
    */
    val compositeDisposable = CompositeDisposable()


    /* Mutable Live Data
    *  In mutable live data , we have setValue() & postValue() as public
    * So these functions can be accessed from outside the viewmodel class as well
    * Note : In LiveData we cannot access the setValue and PostValue outside Viewmodel Class
    */
    val repoLiveData = MutableLiveData<List<Repo>>()

    val repository = RepoRepository(RepoRemoteSource, RepoLocalSource)

    fun getMyStarRepos(username: String) {
        //Controlling the duplication of data
        if (repoLiveData.value != null) {
            return
        }

        //Fetching the data from Repo - The repo further gets data from remote and local source
        //Scedulers.io is for sceduling network requests or io operation
        val reposDisposable = repository.fetchRepos(username)
            //Schedulers - Threading in RxJava is done with help of Schedulers. Scheduler can be thought of as a
            // thread pool managing 1 or more threads. Whenever a Scheduler needs to execute a task, it will take a
            // thread from its pool and run the task in that thread.
            .subscribeOn(Schedulers.io())
            //Observe one is for where to expect the response
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it -> repoLiveData.value = it }

        compositeDisposable.add(reposDisposable)
    }

    fun getLiveData(): LiveData<List<Repo>> = repoLiveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}