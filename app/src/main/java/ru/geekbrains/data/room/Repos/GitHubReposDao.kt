package ru.geekbrains.data.room.Repos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface GitHubReposDao {


    @Query("SELECT * FROM GitHubReposEntity")
    fun getRepos(): Single<List<GitHubReposEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users: List<GitHubReposEntity>)

}