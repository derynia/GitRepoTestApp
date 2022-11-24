package com.gitrepotestapp.db.mapper

import com.gitrepotestapp.db.entity.DownloadedRepo
import com.gitrepotestapp.network.model.UserRepoItem

class RepoToDownloadedRepoMapper : Mapper<UserRepoItem, DownloadedRepo> {
    override fun mapTo(inValue: UserRepoItem) = DownloadedRepo(
        id = inValue.id,
        name = inValue.name,
        full_name = inValue.full_name
    )
}
