package com.example.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.R
import com.example.databinding.FragmentHomeBinding
import com.example.screens.home.welcome_viewpager.WelcomeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "We will only access viewModel after onViewCreated()"
        }

        ViewModelProvider(this, HomeViewModel.Factory(activity.application))
            .get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // Food list
        val foodAdapter = HomeAdapter()
        binding.homeFoodList.adapter = foodAdapter
        viewModel.foodList.observe(viewLifecycleOwner, {
            it?.let {
                Timber.i("Foods: %s", it)
                foodAdapter.submitList(it)
            }
        })

        // Welcome view pager
        val welcomePagerAdapter = WelcomeViewPagerAdapter(this.activity as AppCompatActivity)
        binding.homeWelcomeViewPager2.adapter = welcomePagerAdapter
        TabLayoutMediator(binding.homeWelcomeTabLayout, binding.homeWelcomeViewPager2) { tab, position ->

        }.attach()

        return binding.root
    }

}