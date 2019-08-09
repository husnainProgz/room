package com.example.reactiveprog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reactiveprog.R
import com.example.reactiveprog.db.Repo
import kotlinx.android.synthetic.main.star_view.view.*

class GithubRepoAdapter : RecyclerView.Adapter<GithubRepoAdapter.StarRepoViewHolder>() {

    val data = ArrayList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarRepoViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.star_view,parent,false)
        return StarRepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StarRepoViewHolder, position: Int) {

        holder.repoName.text = data[position].name
        holder.desc.text = data[position].desc
        holder.lang.text = data[position].lang

    }

    public fun addRepos(repos:ArrayList<Repo>){
        data.clear()
        data.addAll(repos)
        notifyDataSetChanged()
    }

    class StarRepoViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val repoName = view.repoName
        val desc = view.desc
        val lang = view.lang
        val starCount = view.starCount
    }
}