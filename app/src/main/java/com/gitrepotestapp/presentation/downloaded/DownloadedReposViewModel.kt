package com.gitrepotestapp.presentation.downloaded

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitrepotestapp.db.repository.DownloadedRepository
import com.gitrepotestapp.presentation.state.DownloadedRepoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadedReposViewModel @Inject constructor(
    private val downloadedRepository: DownloadedRepository
) : ViewModel() {
    private val reposData = MutableStateFlow(DownloadedRepoListState())
    val repos: StateFlow<DownloadedRepoListState> = reposData

    fun fetchData() {
        reposData.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val list = downloadedRepository.getList()
            reposData.update {
                it.copy(
                    isLoading = false,
                    isDataCollected = true,
                    successResult = list
                )
            }
        }
    }
}