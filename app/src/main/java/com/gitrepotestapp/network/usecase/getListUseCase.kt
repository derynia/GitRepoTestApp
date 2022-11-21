package com.gitrepotestapp.network.usecase

import com.gitrepotestapp.network.interactor.NetworkInteractor
import com.gitrepotestapp.network.model.UserRepoItem
import com.gitrepotestapp.util.Resource
import javax.inject.Inject

class UserReposUseCase @Inject constructor(
    private val networkInteractor: NetworkInteractor
) {
    var userName: String = ""

    suspend fun execute(): Resource<List<UserRepoItem>> =
        networkInteractor.getUserReposList(userName)
}