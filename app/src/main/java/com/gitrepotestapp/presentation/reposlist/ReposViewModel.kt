package com.gitrepotestapp.presentation.reposlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitrepotestapp.db.mapper.RepoToDownloadedRepoMapper
import com.gitrepotestapp.db.repository.DownloadedRepository
import com.gitrepotestapp.di.IoDispatcher
import com.gitrepotestapp.network.model.UserRepoItem
import com.gitrepotestapp.network.usecase.UserReposUseCase
import com.gitrepotestapp.presentation.state.RepoListState
import com.gitrepotestapp.util.ReposDownLoadManager
import com.gitrepotestapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val userReposUseCase: UserReposUseCase,
    private val downLoadManager: ReposDownLoadManager,
    private val downloadedRepo: DownloadedRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val mapper: RepoToDownloadedRepoMapper
) : ViewModel() {
    private val reposData = MutableStateFlow(RepoListState())
    val repos: StateFlow<RepoListState> = reposData

    fun fetchData(userName: String) {
        if (userName.isEmpty()) reposData.update {
            it.copy(
                isLoading = false,
                isSuccessful = true,
                successResult = listOf()
            )
        }

        reposData.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            userReposUseCase.userName = userName

            when (val result = userReposUseCase.execute()) {
                is Resource.Success -> reposData.update {
                    it.copy(
                        isLoading = false,
                        isSuccessful = true,
                        successResult = result.data ?: listOf()
                    )
                }
                else -> reposData.update {
                    it.copy(
                        isLoading = false,
                        isSuccessful = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun errorMessageShown() {
        reposData.update { it.copy(isLoading = false, isSuccessful = true, errorMessage = null) }
    }

    fun downLoadRepo(item: UserRepoItem) {
        viewModelScope.launch(dispatcher) {
            downloadedRepo.insert(mapper.mapTo(item))
        }
        downLoadManager.download(item)
    }
}
