package com.gitrepotestapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitrepotestapp.db.repository.DownloadedRepository
import com.gitrepotestapp.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val downloadedRepository: DownloadedRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    fun onRepoDownloaded(downloadFilePath: String, repoId: String) {
        viewModelScope.launch(dispatcher) {
            downloadedRepository.updatePath(downloadFilePath, repoId.toInt())
        }
    }
}
