package ru.geekbrains.mvpuser

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.data.ReposRepository.GitHubReposRepository
import ru.geekbrains.data.UserRepository.GitHubUserRepository
import javax.inject.Inject

class UserPresenter(
    private val userLogin: String,

    ) : MvpPresenter<UserView>() {

    @Inject
    lateinit var userRepository: GitHubUserRepository

    @Inject
    lateinit var reposRepository: GitHubReposRepository

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        updateReposContent(userLogin)
        updateUserContent(userLogin)

    }

    fun updateUserContent(userLogin: String) {
        userRepository
            .getUserByLogin(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showInfo(it)

            },
                {
                    //todo
                })
    }


    fun updateReposContent(userLogin: String) {
        reposRepository
            .getRepositoriesList(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showRecyclerList(it)
            },
                {
                    //todo
                })
    }



}