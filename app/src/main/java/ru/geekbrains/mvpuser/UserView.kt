package ru.geekbrains.mvpuser

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.data.GitHubUser

interface UserView : MvpView {

    /**
     * Показывает информацию о пользователе.
     * @param user пользователь
     */
    @SingleState
    fun showInfo(user: GitHubUser)

}