package ru.geekbrains.DI

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.geekbrains.MainActivity
import ru.geekbrains.mvpuser.UserPresenter
import ru.geekbrains.mvpuser.di.RepositoryReposModule
import ru.geekbrains.mvpuser.di.RoomReposModule
import ru.geekbrains.mvpuser.di.UserComponent
import ru.geekbrains.mvpusers.UsersPresenter
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        CiceroneModule::class,
        RepositoryModule::class,
        RoomModule::class,
    ]
)

interface AppComponent {


    fun provideUserComponent(): UserComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
}