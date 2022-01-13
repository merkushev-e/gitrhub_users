package ru.geekbrains.DI

import dagger.Module
import dagger.Provides
import ru.geekbrains.data.GitHubReposRepository
import ru.geekbrains.data.GitHubReposRepositoryImpl

import ru.geekbrains.data.retrofit.GitHubApi
import ru.geekbrains.data.room.Repos.GitHubReposDb


@Module
class RepositoryReposModule {
    @Provides
    fun provideRepository(
        api: GitHubApi,
        dbUsers: GitHubReposDb,
    ): GitHubReposRepository {
        return GitHubReposRepositoryImpl(api,dbUsers)
    }
}
