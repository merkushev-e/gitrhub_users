package ru.geekbrains.data.UserRepository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.GitHubUser

interface GitHubUserRepository {

    fun getUsers(): Single<List<GitHubUser>>

    fun getUserByLogin(userId: String): Single<GitHubUser>


}