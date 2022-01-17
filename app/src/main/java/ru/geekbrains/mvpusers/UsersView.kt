package ru.geekbrains.mvpusers

import android.view.View
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState
import moxy.viewstate.strategy.alias.Skip
import ru.geekbrains.data.GitHubUser

interface UsersView : MvpView {


    @SingleState
    fun showUsers(users: List<GitHubUser>)

    @AddToEndSingle
    fun showStateLoader(visibility: Boolean)

    @AddToEndSingle
    fun showError(action: (View) -> Unit)

}