package com.gitrepotestapp.db.dao

import androidx.room.*
import com.gitrepotestapp.db.entity.DownloadedRepo

@Dao
interface DownloadedReposDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(downloadedItem: DownloadedRepo)

    @Query("SELECT * FROM DownloadedRepo")
    fun getAll(): List<DownloadedRepo>

    @Delete
    fun delete(downloadedItem: DownloadedRepo)

    @Update
    fun setDeleted(downloadedItem: DownloadedRepo)
}