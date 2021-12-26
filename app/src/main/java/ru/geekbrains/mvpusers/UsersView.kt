package ru.geekbrains.mvpusers

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.data.GitHubUser

interface UsersView : MvpView {

    /**
     * Показывает список пользователей.
     * @param users список пользователей
     */
    @SingleState
    fun showUsers(users: List<GitHubUser>)

}