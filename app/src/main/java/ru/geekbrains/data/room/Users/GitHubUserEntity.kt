package ru.geekbrains.data.room.Users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitHubUserEntity(
    @PrimaryKey val id: String,
    val login: String,
    val avatarUrl: String,
    val type: String = "",
    val location: String = ""
)
