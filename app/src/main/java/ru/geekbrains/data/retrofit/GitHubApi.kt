package ru.geekbrains.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.geekbrains.data.GitHubUser

interface GitHubApi {

    @GET("/users")
    fun fetchUsers(): Single<List<GitHubUser>>

    @GET("/users/{login}")
    fun fetchUserByLogin(@Path("login") login: String): Single<GitHubUser>

    @GET("/users/{login}/repos")
    fun fetchUserRepositories(@Path("login") login: String): Single<List<GitHubUser>>
}