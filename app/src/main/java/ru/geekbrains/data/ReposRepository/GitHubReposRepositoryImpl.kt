package ru.geekbrains.data.ReposRepository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.GitHubRepos
import ru.geekbrains.data.retrofit.GitHubApi
import ru.geekbrains.data.room.Repos.GitHubReposDb
import ru.geekbrains.data.room.Repos.GitHubReposEntity
import javax.inject.Inject

class GitHubReposRepositoryImpl
@Inject constructor(
    private val gitHubApi: GitHubApi,
    private val localDataSourceReposDao: GitHubReposDb
) : GitHubReposRepository {
    override fun getRepositoriesList(userId: String): Single<List<GitHubRepos>> {
        return localDataSourceReposDao.getGitHubReposDao().getRepos(userId).flatMap {
            if(it.isEmpty()) {
                gitHubApi.fetchUserRepositories(userId)
                    .map {
                            result ->
                        val reposEntity = mapReposListToEntityList(result, userId)
                        localDataSourceReposDao.getGitHubReposDao().saveUsers(reposEntity)
                        result
                    }
            }else{
                Single.just(mapEntityListToReposList(it,userId))
            }
        }
    }


    private fun mapReposListToEntityList(result: List<GitHubRepos>, userId: String): List<GitHubReposEntity> {
        return result.map {GitHubRepos ->
            GitHubReposEntity(
                id = GitHubRepos.id  ?: "",
                name = GitHubRepos.name ?: "",
                user_id = userId
            )
        }
    }

    private fun mapEntityListToReposList(reposEntity: List<GitHubReposEntity>,  userId: String) : List<GitHubRepos>{
        return reposEntity.map {
                GitHubReposEntity ->
            GitHubRepos(
                id = GitHubReposEntity.id,
                name = GitHubReposEntity.name,
                user_id = userId,
            )
        }
    }
}