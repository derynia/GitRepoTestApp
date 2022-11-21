package com.gitrepotestapp.db.repository

import com.gitrepotestapp.db.ReposDb
import com.gitrepotestapp.db.entity.DownloadedRepo
import javax.inject.Inject

class DownloadedRepository @Inject constructor(
    db: ReposDb
) {
    private val dao = db.downloadedItemsDao()

    fun insert(downloadedRepo: DownloadedRepo) = dao.insert(downloadedRepo)

    fun updatePath(filePath: String, id: Int) = dao.updatePath(filePath, id)

    fun getList() = dao.getAll()

    fun getListAsFlow() = dao.getAllAsFlow()
}
