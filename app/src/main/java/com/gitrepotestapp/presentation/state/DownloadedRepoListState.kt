package com.gitrepotestapp.presentation.state

import com.gitrepotestapp.db.entity.DownloadedRepo

data class DownloadedRepoListState(
    override val isLoading: Boolean = false,
    val isDataCollected: Boolean = false,
    val successResult: List<DownloadedRepo> = listOf()
) : IProgressable

