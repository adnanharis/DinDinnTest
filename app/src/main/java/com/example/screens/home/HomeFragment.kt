package com.example.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.R
import com.example.databinding.FragmentHomeBinding
import com.example.screens.home.foods_viewpager.FoodsAdapter
import com.example.screens.home.welcome_viewpager.WelcomeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false
        )

        // Welcome view pager
        val welcomePagerAdapter = WelcomeViewPagerAdapter(this.activity as AppCompatActivity)
        binding.homeWelcomeViewPager2.adapter = welcomePagerAdapter
        TabLayoutMediator(
            binding.homeWelcomeTabLayout,
            binding.homeWelcomeViewPager2
        ) { tab, position ->

        }.attach()

        // Foods section
        val foodCategories = resources.getStringArray(R.array.food_categories)
        val doppelgangerAdapter =
            FoodsAdapter(this.activity as AppCompatActivity, foodCategories.size)
        binding.foodsViewPager.adapter = doppelgangerAdapter
        TabLayoutMediator(binding.foodsTabLayout, binding.foodsViewPager) { tab, position ->
            tab.text = foodCategories[position]
        }.attach()

        binding.homeFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_checkoutFragment)
        }

        return binding.root
    }

}