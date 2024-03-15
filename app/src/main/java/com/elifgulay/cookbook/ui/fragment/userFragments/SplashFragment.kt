package com.elifgulay.cookbook.ui.fragment.userFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.Navigation
import com.elifgulay.cookbook.MainActivity
import com.elifgulay.cookbook.NavigatorActivity
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.databinding.FragmentSplashBinding
import com.elifgulay.cookbook.util.go
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding:FragmentSplashBinding
    private lateinit var auth:FirebaseAuth



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //To handle all authentication-related tasks within the app we accessed the instance of FirebaseAuth class
        auth=FirebaseAuth.getInstance()

        //If there is a registered account and the user is not logged out, go directly to the home fragment
        auth.currentUser?.let {
            activity?.let {
                startActivity(Intent(it, NavigatorActivity::class.java))
                it.finish()
            }
        }

        //To switch from splash to login fragment when startCookingBtn is pressed
        binding.startCookingBtn.setOnClickListener {
            Navigation.go(it,R.id.splashToLogin)
        }

    }
}