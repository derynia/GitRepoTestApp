package com.gitrepotestapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gitrepotestapp.db.dao.DownloadedReposDao
import com.gitrepotestapp.db.entity.DownloadedRepo

@Database(entities = [DownloadedRepo::class], version = 1)

abstract class ReposDb : RoomDatabase() {

    internal abstract fun downloadedItemsDao(): DownloadedReposDao

    companion object {
        fun getInstance(context: Context): ReposDb {
            return Room.databaseBuilder(context, ReposDb::class.java, "repos_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}