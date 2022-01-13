package ru.geekbrains.data

import io.reactivex.rxjava3.core.Single

interface GitHubReposRepository {

    fun getRepositoriesList(userId: String): Single<List<GitHubRepos>>
}