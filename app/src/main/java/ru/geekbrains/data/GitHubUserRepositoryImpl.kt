package ru.geekbrains.data

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class GitHubUserRepositoryImpl : GitHubUserRepository {

    private val gitHubApi = GitHubApiFactory.create()

    override fun getUsers(): Single<List<GitHubUser>> {
         return gitHubApi.fetchUsers()
    }

    override fun getUserByLogin(userId: String): Single<GitHubUser>{
        return gitHubApi.fetchUserByLogin(userId).subscribeOn(Schedulers.io())
    }
}