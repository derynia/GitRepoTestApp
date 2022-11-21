package com.gitrepotestapp

import android.Manifest
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gitrepotestapp.databinding.ActivityMainBinding
import com.gitrepotestapp.presentation.ViewPagerFragmentStateAdapter
import com.gitrepotestapp.presentation.extensions.checkSelfPermissionCompat
import com.gitrepotestapp.presentation.extensions.requestPermissionsCompat
import com.gitrepotestapp.presentation.extensions.shouldShowRequestPermissionRationaleCompat
import com.gitrepotestapp.presentation.extensions.showSnackbar
import com.gitrepotestapp.util.ReposDownLoadManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val PERMISSION_REQUEST_STORAGE = 0

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var repoDownLoadManager: ReposDownLoadManager
    private val tabNames by lazy {
        arrayOf(getString(R.string.search), getString(R.string.downloaded))
    }
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val permissionsMap = mapOf(
        Manifest.permission.READ_EXTERNAL_STORAGE to R.string.extstorage_permission_denied,
        Manifest.permission.WRITE_EXTERNAL_STORAGE to R.string.extstorage_permission_write_denied)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GitRepoTestApp)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        requestPermissionOnStart()
        registerReceiver(onDownloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    private val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val dmQuery = DownloadManager.Query()
            dmQuery.setFilterById(intent.extras?.getLong(DownloadManager.EXTRA_DOWNLOAD_ID) ?: 0)
            val cursor = repoDownLoadManager.downloadManager.query(dmQuery)
            with(cursor) {
                if (moveToFirst()) {
                    val indexColStatus = getColumnIndex(DownloadManager.COLUMN_STATUS)

                    indexColStatus.takeIf { it >= 0 && getInt(indexColStatus) == DownloadManager.STATUS_SUCCESSFUL }
                        ?.let {
                            val indexColUrl = getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)
                            val indexColDesc = getColumnIndex(DownloadManager.COLUMN_DESCRIPTION)

                            if (indexColUrl >= 0 && indexColDesc >= 0) {
                                viewModel.onRepoDownloaded(
                                    getString(indexColUrl),
                                    getString(indexColDesc)
                                )
                            }
                        }
                }
            }
        }
    }

    override fun onDestroy() {
        unregisterReceiver(onDownloadComplete)
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            permissions.forEachIndexed { index, value ->
                if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                    permissionsMap[value]?.let { stringRes ->
                        binding.mainLayout.showSnackbar(getString(stringRes), Snackbar.LENGTH_SHORT)
                    }
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun requestPermissionOnStart() {
        if (checkSelfPermissionCompat(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermissionCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
        } else {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        val permissionsArray: ArrayList<String> = arrayListOf()
        permissionsArray.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        permissionsArray.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            permissionsArray.remove(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionsArray.remove(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        // Permission has not been granted and must be requested.
        if (permissionsArray.size > 0) {
            requestPermissionsCompat(permissionsArray.toTypedArray(), PERMISSION_REQUEST_STORAGE)
        }
    }

    private fun setupView() {
        binding.viewPagerFragments.adapter = ViewPagerFragmentStateAdapter(this)
        TabLayoutMediator(binding.tabLayoutMain, binding.viewPagerFragments) { tab, position -> tab.text = tabNames[position] }.attach()
    }
}
