package com.gitrepotestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gitrepotestapp.databinding.ActivityMainBinding
import com.gitrepotestapp.presentation.ViewPagerFragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val tabNames by lazy {
        arrayOf(getString(R.string.search), getString(R.string.downloaded))
    }
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GitRepoTestApp)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        binding.viewPagerFragments.adapter = ViewPagerFragmentStateAdapter(this)
        TabLayoutMediator(binding.tabLayoutMain, binding.viewPagerFragments)
        { tab, position -> tab.text = tabNames[position] }.attach()
    }

}