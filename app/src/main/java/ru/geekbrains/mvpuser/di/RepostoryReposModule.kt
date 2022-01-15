package ru.geekbrains.mvpuser.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.data.ReposRepository.GitHubReposRepository
import ru.geekbrains.data.ReposRepository.GitHubReposRepositoryImpl
import ru.geekbrains.data.retrofit.GitHubApi
import ru.geekbrains.data.room.Repos.GitHubReposDb


@Module
class RepositoryReposModule {

    @UserScope
    @Provides
    fun provideRepository(
        api: GitHubApi,
        dbUsers: GitHubReposDb,
    ): GitHubReposRepository {
        return GitHubReposRepositoryImpl(api,dbUsers)
    }
}