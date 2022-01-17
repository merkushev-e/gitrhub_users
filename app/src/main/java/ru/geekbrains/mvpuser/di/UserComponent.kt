package ru.geekbrains.mvpuser.di

import dagger.Subcomponent
import ru.geekbrains.mvpuser.UserPresenter
import javax.inject.Scope


@UserScope
@Subcomponent(
    modules = [
        RoomReposModule::class,
        RepositoryReposModule::class
    ]
)
interface UserComponent {

    @Subcomponent.Builder
        interface Builder{
            fun build(): UserComponent
        }

    fun inject(userPresenter: UserPresenter)
}

@Scope
annotation class UserScope
