package com.gitrepotestapp.util

import android.Manifest
import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.gitrepotestapp.network.model.UserRepoItem
import com.gitrepotestapp.presentation.extensions.isPermissionGranted
import java.io.File
import javax.inject.Inject

class ReposDownLoadManager @Inject constructor(
    private val application: Application,
) {
    val downloadManager = application.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    companion object {
        private const val REPLACE_TEXT = "{archive_format}{/ref}"
        private const val NEW_TEXT = "zipball"
        private const val SUFFIX = ".zip"
    }

    fun download(item: UserRepoItem): Long {
        if (!application.isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) return -1
        val downloadUri = item.archive_url.replace(REPLACE_TEXT, NEW_TEXT)
        val uri: Uri = Uri.parse(downloadUri)
        val fileName = item.name + SUFFIX
        // I specially used the public download directory for debug purposes. It's possible to use:
        // val saveUri = Uri.fromFile(File(application.getExternalFilesDir(null), fileName))
        val saveUri = Uri.fromFile(
            File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                fileName
            )
        )
        val request = DownloadManager.Request(uri).apply {
            setTitle(fileName)
            setDescription(item.id.toString())
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationUri(saveUri)
            setAllowedOverMetered(true)
            setAllowedOverRoaming(true)
        }
        return downloadManager.enqueue(request)
    }
}
