package com.example.reactiveprog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reactiveprog.adapter.GithubRepoAdapter
import com.example.reactiveprog.db.Repo
import com.example.reactiveprog.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_my_stars_repos.*

class MyStarsRepos : AppCompatActivity() {

    val repoList = ArrayList<Repo>()

    /*LATEINT
     Kotlin requires all member properties to be intialized at instance construction level.
     But sometimes constructor does not has all the information So,
     In order to not have to make those properties nullable, you can use a late-initialized property
    */
    private lateinit var repoViewModel : RepoViewModel
    private lateinit var repoAdapter :GithubRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_stars_repos)

        val linearLayoutmg = LinearLayoutManager(applicationContext)
        myStarList.layoutManager = linearLayoutmg

        repoAdapter = GithubRepoAdapter()

        myStarList.adapter = repoAdapter

        repoViewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)

        getStarRepos(repoViewModel)
        observeMyStars(repoViewModel)
    }

    fun getStarRepos(viewModel : RepoViewModel){
      viewModel.getMyStarRepos("mrabelwahed")
    }

    fun observeMyStars(viewModel: RepoViewModel){
        viewModel.getLiveData().observe(this, Observer {
            repos -> repoAdapter.addRepos(ArrayList(repos))
        })
    }
}
