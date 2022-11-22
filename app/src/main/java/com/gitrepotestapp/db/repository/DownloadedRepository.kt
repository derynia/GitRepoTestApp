package com.gitrepotestapp.db.repository

import com.gitrepotestapp.db.ReposDb
import com.gitrepotestapp.db.entity.DownloadedRepo
import javax.inject.Inject

class DownloadedRepository @Inject constructor(
    private val db: ReposDb
) {
    private val dao = db.downloadedItemsDao()

    fun insert(downloadedRepo: DownloadedRepo) = dao.insert(downloadedRepo)

    fun getList() = dao.getAll()
}