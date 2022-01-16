package ru.geekbrains.mvpuser.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.geekbrains.data.GitHubRepos
import ru.geekbrains.databinding.ViewReposBinding
import ru.geekbrains.databinding.ViewUserBinding
import ru.geekbrains.recycler.UserDiff
import ru.geekbrains.recycler.UserViewHolder
import ru.geekbrains.recycler.UsersAdapter

class ReposAdapter(private val onReposClickListener: OnReposClickListener?): ListAdapter<GitHubRepos, ReposViewHolder>(ReposDiff()) {
    interface OnReposClickListener {
        fun onReposClicked(repos: GitHubRepos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val itemBinding = ViewReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReposViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(getItem(position), onReposClickListener)
    }


}