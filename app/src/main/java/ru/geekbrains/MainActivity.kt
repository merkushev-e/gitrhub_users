package ru.geekbrains

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.App.Navigation.navigatorHolder
import ru.geekbrains.App.Navigation.router
import ru.geekbrains.mvpusers.UsersScreen
import ru.geekbrains.navigation.CustomNavigator

class MainActivity : AppCompatActivity() {
    private val navigator = CustomNavigator(activity = this, R.id.content)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            router.navigateTo(UsersScreen)
        }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}