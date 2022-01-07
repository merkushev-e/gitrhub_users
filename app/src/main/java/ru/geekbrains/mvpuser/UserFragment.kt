package ru.geekbrains.mvpuser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.App
import ru.geekbrains.R
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.databinding.ViewUserDetailBinding

class UserFragment: MvpAppCompatFragment(R.layout.view_user_detail), UserView {

    private lateinit var viewBinding: ViewUserDetailBinding

    private val userLogin: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            userLogin = userLogin,
        ).apply {  App.instance.component.inject(this)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = ViewUserDetailBinding.bind(view)
        viewBinding.userLogin.text = userLogin
    }

    override fun showInfo(user: GitHubUser) {
        viewBinding.location.text = user.location
        Glide.with(viewBinding.userAvatar.context)
            .load(user.avatarUrl)
            .into(viewBinding.userAvatar)
    }

    companion object {
        private const val ARG_USER_LOGIN = "arg_user_login"

        fun newInstance(userId: String): Fragment =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_LOGIN, userId)
                }
            }
    }
}