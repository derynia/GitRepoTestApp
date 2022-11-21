package com.gitrepotestapp.db.dao

import androidx.room.*
import com.gitrepotestapp.db.entity.DownloadedRepo
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadedReposDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(downloadedItem: DownloadedRepo)

    @Query("SELECT * FROM DownloadedRepo")
    fun getAll(): List<DownloadedRepo>

    @Query("SELECT * FROM DownloadedRepo")
    fun getAllAsFlow(): Flow<List<DownloadedRepo>>

    @Delete
    fun delete(downloadedItem: DownloadedRepo)

    @Query("UPDATE DownloadedRepo SET file_path = :filePath WHERE id = :id")
    fun updatePath(filePath: String, id: Int)
}
