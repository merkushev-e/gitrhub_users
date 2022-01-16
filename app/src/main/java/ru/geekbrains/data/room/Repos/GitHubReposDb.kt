package ru.geekbrains.data.room.Repos

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(exportSchema = false, entities = [GitHubReposEntity::class], version = 1)
abstract class GitHubReposDb: RoomDatabase() {
    abstract fun getGitHubReposDao(): GitHubReposDao
}
