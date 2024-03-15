package com.elifgulay.cookbook.ui.fragment.bottomNavFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.elifgulay.cookbook.MainActivity
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.databinding.FragmentHomeBinding
import com.elifgulay.cookbook.ui.adapter.CategoryButtonAdapter
import com.elifgulay.cookbook.ui.adapter.CategoryRecyclerAdapter
import com.elifgulay.cookbook.ui.viewModel.mainViewModel.HomeViewModel
import com.elifgulay.cookbook.util.go
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Bind xml and fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.auth = FirebaseAuth.getInstance()
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homeObject=this
        observeMealList()
        observeButtonList()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Method to run after typing the full name of the meal to be searched and pressing the search button
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query,requireView())
               return true
            }
            //Method to run each time a letter is entered in search
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText,requireView())
                return true
            }
        })
    }

    fun observeMealList(){
        viewModel.mealList.observe(viewLifecycleOwner) {
            it?.let {
                //To get the meal list on the recyclerView from the API
                binding.recyclerCategoryObject = CategoryRecyclerAdapter(requireContext(), it)
            }

        }
    }
    fun observeButtonList(){
        viewModel.categoryBtnList.observe(viewLifecycleOwner) {
            it?.let {
                //To get the categories on the recyclerView from the API
                binding.recyclerCategoryBtnObject = CategoryButtonAdapter(requireContext(), it, viewModel)
            }

        }
    }

    //The method that will run when the user wants to log out of account
    fun exitApp(auth:FirebaseAuth) {
        //To display the logout window on the screen
        MaterialAlertDialogBuilder(requireContext()).setTitle("Exit Account?").setMessage("Do you want to log out your account?")
            .setPositiveButton("Yes", { a, b ->
                auth.signOut()
                startActivity(Intent(requireActivity(),MainActivity::class.java)) //To switch to MainActivity after user logs out
               this.requireActivity().finish()
            }).setNegativeButton("No",{a,b->}).show()
    }



}

