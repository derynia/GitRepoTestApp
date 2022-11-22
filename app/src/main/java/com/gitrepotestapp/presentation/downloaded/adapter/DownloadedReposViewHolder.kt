package com.gitrepotestapp.presentation.downloaded.adapter

import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.recyclerview.widget.RecyclerView
import com.gitrepotestapp.databinding.DownloadedRepoListItemBinding
import com.gitrepotestapp.db.entity.DownloadedRepo

class DownloadedReposViewHolder(private val binding: DownloadedRepoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: DownloadedRepo
    ) {
        with(binding) {
            textRepoName.text = Html.fromHtml("<a href=\"${item.file_path}\">${item.name}</a>")
            textRepoName.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}