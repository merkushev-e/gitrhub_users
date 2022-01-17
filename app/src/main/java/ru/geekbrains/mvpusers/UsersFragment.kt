package ru.geekbrains.mvpusers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.App
import ru.geekbrains.R
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.databinding.ViewUsersBinding
import ru.geekbrains.recycler.UsersAdapter

class UsersFragment: MvpAppCompatFragment(R.layout.view_users), UsersView, UsersAdapter.OnUserClickListener {

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.component.inject(this)
        }
    }

    private val usersAdapter = UsersAdapter(this)
    private lateinit var viewBinding: ViewUsersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = ViewUsersBinding.bind(view)
        viewBinding.usersRecycler.adapter = usersAdapter
    }

    override fun showUsers(users: List<GitHubUser>) {
        usersAdapter.submitList(users)
    }

    override fun showStateLoader(visibility: Boolean) {
        val visible = if (visibility){
            View.VISIBLE
        } else{
            View.GONE
        }
        viewBinding.progressBar.visibility = visible
    }

    override fun showError(action: (View) -> Unit) {
        Snackbar
            .make(viewBinding.usersMain, "Connection Error", Snackbar.LENGTH_INDEFINITE)
            .setAction("Reload") {action(viewBinding.usersMain)}
            .show()

    }
    override fun onUserPicked(user: GitHubUser) {
        presenter.goToNextScreen(user.login!!)
    }

    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

//    override fun onBackPressed(): Boolean {
//        return presenter.backPressed()
//    }


}