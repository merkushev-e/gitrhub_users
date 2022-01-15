package ru.geekbrains.mvpuser

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.App
import ru.geekbrains.R
import ru.geekbrains.data.GitHubRepos
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.databinding.ViewUserDetailBinding
import ru.geekbrains.mvpuser.recycler.ReposAdapter
import ru.geekbrains.recycler.UsersAdapter

class UserFragment: MvpAppCompatFragment(R.layout.view_user_detail), UserView, ReposAdapter.OnReposClickListener {

    private val reposAdapter = ReposAdapter(this)
    private lateinit var viewBinding: ViewUserDetailBinding

    private val userLogin: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    private val userComponent by lazy {
        App.instance.initUserComponent()
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            userLogin = userLogin,
        ).apply {
            App.instance.component.inject(this)
            userComponent.inject(this)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = ViewUserDetailBinding.bind(view)
        viewBinding.userLogin.text = userLogin
        viewBinding.repositoriesRecycler.adapter = reposAdapter
    }

    override fun showInfo(user: GitHubUser) {
        viewBinding.location.text = user.location
        Glide.with(viewBinding.userAvatar.context)
            .load(user.avatarUrl)
            .into(viewBinding.userAvatar)

    }

    override fun showRecyclerList(repos: List<GitHubRepos>) {

        reposAdapter.submitList(repos)
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

    override fun onReposClicked(repos: GitHubRepos) {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
}