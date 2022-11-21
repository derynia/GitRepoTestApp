package com.gitrepotestapp.presentation.downloaded.adapter

import android.text.Html
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.gitrepotestapp.databinding.DownloadedRepoListItemBinding
import com.gitrepotestapp.db.entity.DownloadedRepo

class DownloadedReposViewHolder(private val binding: DownloadedRepoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: DownloadedRepo,
        onTextClick: (DownloadedRepo) -> Unit
    ) {
        with(binding) {
            if (item.isDownLoaded()) {
                textRepoName.text =
                    Html.fromHtml("<a href=\"${item.file_path}\">${item.full_name}</a>")
                imageDownloaded.isVisible = true
                textRepoName.setOnClickListener {
                    onTextClick(item)
                }
            } else {
                textRepoName.text = item.name
                imageDownloaded.isVisible = false
            }
        }
    }
}
