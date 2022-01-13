package ru.geekbrains.data.room.Users

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(exportSchema = false, entities = [GitHubUserEntity::class], version = 1)
abstract class GitHubUserDb: RoomDatabase() {

    abstract fun getGitHubUserDao(): GitHubUsersDao
}