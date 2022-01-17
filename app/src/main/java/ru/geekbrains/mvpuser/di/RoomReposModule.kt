package ru.geekbrains.mvpuser.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.data.room.Repos.GitHubReposDb
import javax.inject.Singleton

@Module
class RoomReposModule {
    companion object {
        private const val DB_NAME = "GitHubRepos.db"
    }

    @UserScope
    @Provides
    fun providesRoomModule(app: Context): GitHubReposDb {
        return Room.databaseBuilder(
            app,
            GitHubReposDb::class.java,
            DB_NAME
        )
            .build()
    }
}