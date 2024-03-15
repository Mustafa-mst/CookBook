package com.elifgulay.cookbook.ui.fragment.bottomNavFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.databinding.FragmentMyRecipeBinding
import com.elifgulay.cookbook.ui.adapter.MyRecipeAdapter
import com.elifgulay.cookbook.ui.viewModel.helperViewModel.MyRecipeShowViewModel
import com.elifgulay.cookbook.util.go
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRecipeFragment : Fragment() {
    private lateinit var binding:FragmentMyRecipeBinding
    private lateinit var viewModel: MyRecipeShowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Bind xml and fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_my_recipe, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(MyRecipeShowViewModel::class.java)


        viewModel.myRecipeList.observe(viewLifecycleOwner){
            //To show the recipes we added on the MyRecipe screen
            binding.myRecipeAdapter= MyRecipeAdapter(requireContext(),it,viewModel)
        }
        //To go to the recipe creation screen when the add button on the MyRecipe screen is pressed
        binding.goToAdd.setOnClickListener {
            Navigation.go(it,R.id.myRecipesToAdd)
        }
    }

}