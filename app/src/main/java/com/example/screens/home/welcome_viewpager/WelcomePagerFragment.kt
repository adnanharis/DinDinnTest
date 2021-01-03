package com.example.screens.home.welcome_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.R
import com.example.databinding.FragmentWelcomePagerBinding

class WelcomePagerFragment : Fragment() {

    companion object {
        val DATA = arrayOf(
            "https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
            "https://www.themealdb.com/images/media/meals/uuuspp1468263334.jpg"
        )

        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val doppelgangerFragment = WelcomePagerFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            doppelgangerFragment.arguments = bundle
            return doppelgangerFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentWelcomePagerBinding>(
            inflater,
            R.layout.fragment_welcome_pager,
            container,
            false
        )

        val position = requireArguments().getInt(ARG_POSITION)
        binding.imageUrl = DATA[position]
        return binding.root
    }

}