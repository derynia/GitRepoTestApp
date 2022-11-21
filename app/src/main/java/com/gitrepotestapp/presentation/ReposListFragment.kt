package com.gitrepotestapp.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gitrepotestapp.R
import com.gitrepotestapp.databinding.FragmentReposBinding
import com.gitrepotestapp.network.model.UserRepoItem
import com.gitrepotestapp.presentation.adapter.ReposAdapter
import com.gitrepotestapp.presentation.extensions.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReposListFragment : Fragment(R.layout.fragment_repos) {
    private val binding: FragmentReposBinding by viewBinding(FragmentReposBinding::bind)
    private val viewModel: ReposViewModel by activityViewModels()
    private val reposAdapter = ReposAdapter { item -> downLoadRepo(item) }

    private fun downLoadRepo(item: UserRepoItem) {
        Toast.makeText(requireContext(), "${item.name} Clicked", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initViews()
        initDataCollectors()
    }

    private fun FragmentReposBinding.initViews() {
        recyclerRepos.adapter = reposAdapter

        editSearch.doAfterTextChanged {
            viewModel.fetchData(it.toString())
        }
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun handleError(message: String?) {
        hideProgress()
        message?.let {
            activity?.showError(getString(R.string.error_header), it)
            viewModel.errorMessageShown()
        }
    }

    private fun initDataCollectors() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.repos.collectLatest { event ->
                    when  {
                        event.isLoading -> showProgress()
                        event.isSuccessful -> {
                            hideProgress()
                            reposAdapter.submitList(event.successResult)
                        }
                        event.errorMessage != null -> handleError(event.errorMessage)
                    }
                }
            }
        }

    }
}