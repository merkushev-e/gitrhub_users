package ru.geekbrains.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.data.GitHubUser

@Database(exportSchema = false, entities = [GitHubUserEntity::class], version = 1)
abstract class GitHubUserDb: RoomDatabase() {

    abstract fun getGitHubUserDao(): GitHubUsersDao
}