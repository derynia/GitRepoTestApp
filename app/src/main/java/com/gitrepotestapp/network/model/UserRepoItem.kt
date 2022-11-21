package com.gitrepotestapp.network.model

data class UserRepoItem(
    val full_name: String,
    val id: Int,
    val name: String,
    val node_id: String,
    val url: String,
    val downloads_url: String
)