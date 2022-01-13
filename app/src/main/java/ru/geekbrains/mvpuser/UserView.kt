package ru.geekbrains.mvpuser

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.data.GitHubRepos
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.data.GitHubUserRepository

interface UserView : MvpView {


    @SingleState
    fun showInfo(user: GitHubUser)

    @AddToEndSingle
    fun showRecyclerList(repos:  List<GitHubRepos>)

}