package ru.geekbrains.mvpuser

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.navigation.CustomRouter
import javax.inject.Inject

class UserPresenter(
    private val userLogin: String,

) : MvpPresenter<UserView>() {

    @Inject
    lateinit var userRepository: GitHubUserRepository

    @Inject
    lateinit var router: Router

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