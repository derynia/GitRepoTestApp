package com.gitrepotestapp.presentation.downloaded

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.content.FileProvider.getUriForFile
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gitrepotestapp.R
import com.gitrepotestapp.databinding.FragmentDownloadedBinding
import com.gitrepotestapp.db.entity.DownloadedRepo
import com.gitrepotestapp.presentation.downloaded.adapter.DownloadedReposAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class DownloadedReposFragment : Fragment(R.layout.fragment_downloaded) {
    private val binding: FragmentDownloadedBinding by viewBinding(FragmentDownloadedBinding::bind)
    private val viewModel: DownloadedReposViewModel by viewModels()
    private val reposAdapter = DownloadedReposAdapter { item -> openDownLoadedFile(item) }

    private fun openDownLoadedFile(item: DownloadedRepo) {
        val imagePath = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "")
        val newFile = File(imagePath, File(item.file_path).name)
        val contentUri: Uri = getUriForFile(
            requireContext(),
            requireContext().applicationContext
                .packageName + ".provider",
            newFile
        )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.setDataAndType(contentUri, "application/zip")

        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initViews()
        initDataCollectors()
    }

    private fun FragmentDownloadedBinding.initViews() {
        recyclerRepos.adapter = reposAdapter
    }

    private fun initDataCollectors() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.repos.collectLatest { event -> reposAdapter.submitList(event) }
            }
        }
    }
}
