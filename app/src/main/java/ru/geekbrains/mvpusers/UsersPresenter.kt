package ru.geekbrains.mvpusers

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.data.UserRepository.GitHubUserRepository
import ru.geekbrains.mvpuser.UserScreen
import javax.inject.Inject

class UsersPresenter: MvpPresenter<UsersView>() {

    @Inject
    lateinit var userRepository: GitHubUserRepository

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
       updateContent()
    }

    fun goToNextScreen(login: String) {
        router.navigateTo(UserScreen(login))
    }

    private fun updateContent() {
        viewState.showStateLoader(true)
        userRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { viewState.showStateLoader(false) }
            .subscribe({
                viewState.showUsers(it)
            },{
                viewState.showError { updateContent() }
            })
    }


}