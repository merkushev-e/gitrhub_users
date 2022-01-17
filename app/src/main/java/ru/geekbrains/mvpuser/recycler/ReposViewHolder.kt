package ru.geekbrains.mvpuser.recycler

import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.data.GitHubRepos
import ru.geekbrains.databinding.ViewReposBinding

class ReposViewHolder(private val viewBinding: ViewReposBinding): RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(repository: GitHubRepos, onReposClickListener: ReposAdapter.OnReposClickListener?){
        viewBinding.reposName.text = repository.name

        viewBinding.root.setOnClickListener{
            onReposClickListener?.onReposClicked(repository)
        }
    }
}