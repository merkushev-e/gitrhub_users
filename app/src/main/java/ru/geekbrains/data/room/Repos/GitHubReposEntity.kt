package ru.geekbrains.data.room.Repos

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class GitHubReposEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val user_id: String,
)