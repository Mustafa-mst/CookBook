package com.elifgulay.cookbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.elifgulay.cookbook.databinding.ActivityNavigatorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigatorActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNavigatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_navigator)

        val navhost=this.supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomNavigationView,navhost.navController)
        binding.bottomNavigationView.apply {
            setOnItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(item, navhost.navController)
                true
            }
            setOnItemReselectedListener {
                navhost.navController.popBackStack(destinationId = it.itemId, inclusive = false)
            }
        }

    }
}