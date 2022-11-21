package com.gitrepotestapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gitrepotestapp.databinding.RepoListItemBinding
import com.gitrepotestapp.network.model.UserRepoItem

class ReposAdapter(
    private val onItemClick : (UserRepoItem) -> Unit
) : ListAdapter<UserRepoItem, ReposViewHolder>(ReposComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding = RepoListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClick)
        }
    }
}
