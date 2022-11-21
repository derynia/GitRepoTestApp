package com.gitrepotestapp.presentation.reposlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gitrepotestapp.network.model.UserRepoItem

class ReposComparator : DiffUtil.ItemCallback<UserRepoItem>() {
    override fun areItemsTheSame(oldItem: UserRepoItem, newItem: UserRepoItem) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: UserRepoItem, newItem: UserRepoItem) =
        oldItem.hashCode() == newItem.hashCode()
}
