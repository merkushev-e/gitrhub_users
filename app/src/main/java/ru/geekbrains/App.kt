package ru.geekbrains

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import ru.geekbrains.navigation.CustomRouter

class App: Application() {

    companion object Navigation {

        private val cicerone: Cicerone<CustomRouter> by lazy {
            Cicerone.create(CustomRouter())
        }
        val navigatorHolder = cicerone.getNavigatorHolder()
        val router = cicerone.router
    }
}