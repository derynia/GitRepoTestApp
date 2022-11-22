package com.gitrepotestapp.presentation.downloaded.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gitrepotestapp.databinding.DownloadedRepoListItemBinding
import com.gitrepotestapp.db.entity.DownloadedRepo

class DownloadedReposAdapter : ListAdapter<DownloadedRepo, DownloadedReposViewHolder>(
    DownloadedReposComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadedReposViewHolder {
        val binding = DownloadedRepoListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DownloadedReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DownloadedReposViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
