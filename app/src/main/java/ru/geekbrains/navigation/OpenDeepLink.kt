package ru.geekbrains.navigation

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward
import ru.geekbrains.mvpuser.UserScreen


class OpenDeepLink(private val deepLinkUserId: String) : CustomRouter.Command, Command {

    override fun execute(navigator: CustomNavigator) {
        navigator.applyCommand(Forward(UserScreen(deepLinkUserId)))
    }
}