package com.example.screens.home.welcome_viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class WelcomeViewPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return WelcomePagerFragment.DATA.size
    }

    override fun createFragment(position: Int): Fragment {
        return WelcomePagerFragment.getInstance(position)
    }
}