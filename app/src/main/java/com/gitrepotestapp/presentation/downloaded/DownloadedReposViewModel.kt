package com.gitrepotestapp.presentation.downloaded

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitrepotestapp.db.entity.DownloadedRepo
import com.gitrepotestapp.db.repository.DownloadedRepository
import com.gitrepotestapp.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadedReposViewModel @Inject constructor(
    private val downloadedRepository: DownloadedRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val reposData = MutableStateFlow<List<DownloadedRepo>>(mutableListOf())
    val repos: StateFlow<List<DownloadedRepo>> = reposData

    init {
        viewModelScope.launch(ioDispatcher) {
            reposData.emitAll(downloadedRepository.getListAsFlow())
        }
    }
}
