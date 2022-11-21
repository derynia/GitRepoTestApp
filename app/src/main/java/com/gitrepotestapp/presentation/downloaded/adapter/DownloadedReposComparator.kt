package com.gitrepotestapp.presentation.downloaded.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gitrepotestapp.db.entity.DownloadedRepo

class DownloadedReposComparator : DiffUtil.ItemCallback<DownloadedRepo>() {
    override fun areItemsTheSame(oldItem: DownloadedRepo, newItem: DownloadedRepo) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: DownloadedRepo, newItem: DownloadedRepo) =
        oldItem.hashCode() == newItem.hashCode()
}
