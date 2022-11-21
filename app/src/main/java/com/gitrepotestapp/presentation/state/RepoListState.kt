package com.gitrepotestapp.presentation.state

import com.gitrepotestapp.network.model.UserRepoItem

data class RepoListState(
    override val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val errorMessage: String? = null,
    val successResult: List<UserRepoItem> = listOf()
) : IProgressable
