package com.elifgulay.cookbook.ui.fragment.bottomNavFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.databinding.FragmentFavoriteBinding
import com.elifgulay.cookbook.ui.adapter.FavoriteAdapter
import com.elifgulay.cookbook.ui.viewModel.mainViewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
private lateinit var binding:FragmentFavoriteBinding
private lateinit var viewModel:FavoriteViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Bind xml and fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_favorite,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(FavoriteViewModel::class.java)

        viewModel.favoriteList.observe(viewLifecycleOwner){
            if (it!=null){
                //To show favorited meals in recyclerView
                binding.favoriteAdapter= FavoriteAdapter(requireContext(),it,viewModel)
            }
        }

    }
    //To keep favorites updated
    override fun onResume() {
        viewModel.getFavoriteMeals()
        super.onResume()
    }
}