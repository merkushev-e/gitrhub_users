package ru.geekbrains.data


import androidx.lifecycle.Transformations.map
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.data.room.GitHubUserEntity
import ru.geekbrains.data.room.GitHubUsersDao


class GitHubUserRepositoryImpl(
    private val localDataSourceDao: GitHubUsersDao,
) : GitHubUserRepository {

    private val gitHubApi = GitHubApiFactory.create()

    override fun getUsers(): Single<List<GitHubUser>> {
        return localDataSourceDao.getUsers()
            .flatMap {
                if (it.isEmpty()) {
                    gitHubApi.fetchUsers()
                        .map { resultFromServer ->
                            localDataSourceDao.saveUsers(mapUserListToEntityList(resultFromServer))
                            resultFromServer
                        }
                } else {
                    Single.just(mapEntityListToUserList(it))
                }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun getUserByLogin(userId: String): Single<GitHubUser> {


//        return gitHubApi.fetchUserByLogin(userId).subscribeOn(Schedulers.io())
//        var user = localDataSourceDao.getUserByLogin(userId)
//
//        var userFromServer: Single<GitHubUser> =  gitHubApi.fetchUserByLogin(userId)
//
//        return mapEntityToUserSingle(user)

        return localDataSourceDao.getUserByLogin(userId)
            .flatMap { user ->
                if(user.location == "" ){
                    gitHubApi.fetchUserByLogin(userId)
                        .map{
                            resultFromServer ->
                            val userEntity = mapUserToEntity(resultFromServer)
                            localDataSourceDao.saveUser(userEntity)
                            resultFromServer
                        }
                }else{
                    Single.just(mapEntityToUser(user))
                }
            }
            .subscribeOn(Schedulers.io())

    }


    private fun mapUserListToEntityList(userListFromServer: List<GitHubUser>): List<GitHubUserEntity> {
        return userListFromServer.map { GitHubUser ->
            GitHubUserEntity(
                id = GitHubUser.id ?: "",
                login = GitHubUser.login ?: "",
                avatarUrl = GitHubUser.avatarUrl ?: "",
                type = GitHubUser.type ?: "",
                location = GitHubUser.location ?: ""
            )
        }
    }

    private fun mapEntityListToUserList(userListFromServer: List<GitHubUserEntity>): List<GitHubUser> {
        return userListFromServer.map { GitHubUserEntity ->
            GitHubUser(
                id = GitHubUserEntity.id ?: "",
                login = GitHubUserEntity.login ?: "",
                avatarUrl = GitHubUserEntity.avatarUrl ?: "",
                type = GitHubUserEntity.type ?: "",
                location = GitHubUserEntity.location ?: ""
            )
        }
    }

//    private fun mapEntityToUserSingle(userFromDb: Single<GitHubUserEntity>): Single<GitHubUser> {
//        return userFromDb.flatMap { GitHubUserEntity ->
//            Single.just(
//                GitHubUser(
//                    id = GitHubUserEntity.id ?: "",
//                    login = GitHubUserEntity.login ?: "",
//                    avatarUrl = GitHubUserEntity.avatarUrl ?: "",
//                    type = GitHubUserEntity.type ?: "",
//                    location = GitHubUserEntity.location
//                )
//            )
//
//                .subscribeOn(Schedulers.io())
//        }
//
//    }

    private fun mapUserToEntity(user: GitHubUser) : GitHubUserEntity{
        return GitHubUserEntity(
            id = user.id ?: "",
            login = user.login ?: "",
            avatarUrl = user.avatarUrl ?: "",
            type = user.type ?: "",
            location = user.location
        )
    }
    private fun mapEntityToUser(userEntity: GitHubUserEntity) : GitHubUser{
        return GitHubUser(
            id = userEntity.id ?: "",
            login = userEntity.login ?: "",
            avatarUrl = userEntity.avatarUrl ?: "",
            type = userEntity.type ?: "",
            location = userEntity.location
        )
    }
}