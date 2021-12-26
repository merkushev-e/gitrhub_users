package ru.geekbrains.mvpuser

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.navigation.CustomRouter

class UserPresenter(
    private val userLogin: String,
    private val userRepository: GitHubUserRepository,
    private val router: CustomRouter
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        userRepository
            .getUserByLogin(userLogin)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showInfo(it)
            },
                {
                    //todo
                })

    }

//    fun updateContent() {
//        userRepository.getUsers()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                viewState.showUsers(it)
//            },{
//                val errorMessage = it.message
//                //DisplayError
//            })
//    }
}