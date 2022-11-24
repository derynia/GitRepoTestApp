package com.gitrepotestapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DownloadedRepo")
data class DownloadedRepo(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "full_name") val full_name: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "file_path") val file_path: String = ""
) {
    fun isDownLoaded(): Boolean = file_path.isNotEmpty()
}
