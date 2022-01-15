package ru.geekbrains.DI

import dagger.Module
import dagger.Provides
import ru.geekbrains.data.UserRepository.GitHubUserRepository
import ru.geekbrains.data.UserRepository.GitHubUserRepositoryImpl
import ru.geekbrains.data.retrofit.GitHubApi
import ru.geekbrains.data.room.Users.GitHubUserDb

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(
        api: GitHubApi,
        dbUsers: GitHubUserDb,
    ): GitHubUserRepository {
        return GitHubUserRepositoryImpl(api,dbUsers)
    }
}