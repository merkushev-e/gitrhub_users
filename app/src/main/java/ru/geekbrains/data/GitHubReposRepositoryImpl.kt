package ru.geekbrains.data

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.retrofit.GitHubApi
import ru.geekbrains.data.room.Repos.GitHubReposDb
import ru.geekbrains.data.room.Repos.GitHubReposEntity
import ru.geekbrains.data.room.Users.GitHubUserDb
import javax.inject.Inject

class GitHubReposRepositoryImpl
@Inject constructor(
    private val gitHubApi: GitHubApi,
    private val localDataSourceReposDao: GitHubReposDb
) : GitHubReposRepository {
    override fun getRepositoriesList(userId: String): Single<List<GitHubRepos>> {
        return localDataSourceReposDao.getGitHubReposDao().getRepos().flatMap {
            if(it.isEmpty()) {
                gitHubApi.fetchUserRepositories(userId)
                    .map {
                            result ->
                        val reposEntity = mapReposListToEntityList(result)
                        localDataSourceReposDao.getGitHubReposDao().saveUsers(reposEntity)
                        result
                    }
            }else{
                Single.just(mapEntityListToReposList(it))
            }
        }
    }


    private fun mapReposListToEntityList(result: List<GitHubRepos>): List<GitHubReposEntity> {
        return result.map {GitHubRepos ->
            GitHubReposEntity(
                id = GitHubRepos.id  ?: "",
                name = GitHubRepos.name ?: "",
            )
        }
    }

    private fun mapEntityListToReposList(reposEntity: List<GitHubReposEntity>) : List<GitHubRepos>{
        return reposEntity.map {
                GitHubReposEntity ->
            GitHubRepos(
                id = GitHubReposEntity.id,
                name = GitHubReposEntity.name
            )
        }
    }
}