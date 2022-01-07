package ru.geekbrains.DI

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.App
import ru.geekbrains.data.room.GitHubUserDb
import javax.inject.Singleton

@Module
class RoomModule {
    companion object {
        private const val DB_NAME = "GitHubUsers.db"
    }

    @Singleton
    @Provides
    fun providesRoomModule(app: Context): GitHubUserDb {
        return Room.databaseBuilder(
            app,
            GitHubUserDb::class.java,
            DB_NAME
        )
            .build()
    }
}
