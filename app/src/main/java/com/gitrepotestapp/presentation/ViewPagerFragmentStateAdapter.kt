package com.gitrepotestapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gitrepotestapp.presentation.downloaded.DownloadedReposFragment
import com.gitrepotestapp.presentation.reposlist.ReposListFragment

class ViewPagerFragmentStateAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val itemCount = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ReposListFragment()
            else -> DownloadedReposFragment()
        }
    }

    override fun getItemCount(): Int = itemCount
}