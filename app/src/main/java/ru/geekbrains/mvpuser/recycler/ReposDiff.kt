package ru.geekbrains.mvpuser.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.geekbrains.data.GitHubRepos

class ReposDiff: DiffUtil.ItemCallback<GitHubRepos>() {
    override fun areItemsTheSame(oldItem: GitHubRepos, newItem: GitHubRepos): Boolean {
       return  oldItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: GitHubRepos, newItem: GitHubRepos): Boolean {
        return oldItem == newItem
    }
}