package com.example.screens.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.R
import com.example.databinding.FragmentChakoutBinding

class CheckoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentChakoutBinding>(
            inflater,
            R.layout.fragment_chakout, container, false
        )
        return binding.root
    }

}