package com.gitrepotestapp.presentation.downloaded

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gitrepotestapp.databinding.FragmentDownloadedBinding
import com.gitrepotestapp.presentation.downloaded.adapter.DownloadedReposAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DownloadedReposFragment : Fragment() {
    private val binding: FragmentDownloadedBinding by viewBinding(FragmentDownloadedBinding::bind)
    private val viewModel: DownloadedReposViewModel by viewModels()
    private val reposAdapter = DownloadedReposAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initViews()
        initDataCollectors()
    }

    private fun FragmentDownloadedBinding.initViews() {
        recyclerRepos.adapter = reposAdapter
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun initDataCollectors() {
        viewModel.fetchData()
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.repos.collectLatest { event ->
                    when {
                        event.isLoading -> showProgress()
                        event.isDataCollected -> {
                            hideProgress()
                            reposAdapter.submitList(event.successResult)
                        }
                    }
                }
            }
        }
    }
}
