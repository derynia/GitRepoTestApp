package com.gitrepotestapp.presentation.reposlist.adapter

import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.recyclerview.widget.RecyclerView
import com.gitrepotestapp.databinding.RepoListItemBinding
import com.gitrepotestapp.network.model.UserRepoItem

class ReposViewHolder(private val binding: RepoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: UserRepoItem,
        onItemClick: (UserRepoItem) -> Unit
    ) {
        with(binding) {
            textRepoName.text = Html.fromHtml("<a href=\"${item.html_url}\">${item.name}</a>")
            textRepoName.movementMethod = LinkMovementMethod.getInstance()
            imageDownLoad.setOnClickListener { onItemClick(item) }
        }
    }
}
