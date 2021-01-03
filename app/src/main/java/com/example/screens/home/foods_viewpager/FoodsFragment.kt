package com.example.screens.home.foods_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.R
import com.example.databinding.FragmentFoodsBinding
import timber.log.Timber

class FoodsFragment : Fragment() {

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val fragment = FoodsFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var viewModel: FoodsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentFoodsBinding>(
            inflater,
            R.layout.fragment_foods,
            container,
            false
        )

        val categories = resources.getStringArray(R.array.food_categories)
        val position = requireArguments().getInt(ARG_POSITION)
        val activity = requireNotNull(this.activity) {
            "We will only access viewModel after onViewCreated()"
        }

        viewModel = ViewModelProvider(this, FoodsViewModel.Factory(activity.application, categories[position]))
            .get(FoodsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Food list
        val foodAdapter = FoodListAdapter(FoodListListener { food, isAdded ->
            Timber.i("Is %s Added: %s", food, isAdded)
            // TODO("Part of cart feature")
        })

        binding.homeFoodList.adapter = foodAdapter
        viewModel.foodList.observe(viewLifecycleOwner, {
            it?.let {
                Timber.i("Data: %s", it)
                foodAdapter.submitList(it)
            }
        })

        return binding.root
    }
}