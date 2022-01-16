package ru.geekbrains.data.ReposRepository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.GitHubRepos

interface GitHubReposRepository {

    fun getRepositoriesList(userId: String): Single<List<GitHubRepos>>
}