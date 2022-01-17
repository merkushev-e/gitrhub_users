package ru.geekbrains.mvpuser

import android.view.View
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState
import moxy.viewstate.strategy.alias.Skip
import ru.geekbrains.data.GitHubRepos
import ru.geekbrains.data.GitHubUser

interface UserView : MvpView {


    @SingleState
    fun showInfo(user: GitHubUser)

    @AddToEndSingle
    fun showRecyclerList(repos:  List<GitHubRepos>)

    @AddToEndSingle
    fun showStateLoader(visibility: Boolean)

    @AddToEndSingle
    fun showError(action: (View) -> Unit)

}