package ru.geekbrains.data.room.Users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface GitHubUsersDao {

    @Query("SELECT * FROM GitHubUserEntity")
    fun getUsers(): Single<List<GitHubUserEntity>>

    @Query("SELECT * FROM GitHubUserEntity WHERE login LIKE :login LIMIT 1")
    fun getUserByLogin(login: String): Single<GitHubUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users: List<GitHubUserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: GitHubUserEntity)
}