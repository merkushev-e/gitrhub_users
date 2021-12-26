package ru.geekbrains.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.geekbrains.data.GitHubUser

class UserDiff : DiffUtil.ItemCallback<GitHubUser>() {

    override fun areItemsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
        return oldItem.login == newItem.login
    }

    override fun areContentsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
        return oldItem == newItem
    }
}