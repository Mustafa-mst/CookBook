package com.elifgulay.cookbook.ui.fragment.userFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.elifgulay.cookbook.MainActivity
import com.elifgulay.cookbook.NavigatorActivity
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.databinding.FragmentSignInBinding
import com.elifgulay.cookbook.util.go
import com.elifgulay.cookbook.util.warningSnackBar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

private lateinit var binding:FragmentSignInBinding
private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Bind xml and fragment
        binding= DataBindingUtil.inflate(layoutInflater,R.layout.fragment_sign_in,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInObject=this
        auth= FirebaseAuth.getInstance()
    }

    //To go to the sign up screen when the sign up button on the sign in screen is pressed
    fun navigateToSignUp(view: View){
        Navigation.go(view,R.id.signInToSignUp)
    }

    //To go to the forgot password screen when the forgot button on the sign in screen is pressed
    fun navigateToForgot(view: View){
        Navigation.go(view,R.id.signInToForgot)
    }

    //The method that will run when the user tries to log in
     fun signIn(email: String, password: String, view: View,progress:ProgressBar) {

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            progress.visibility=View.VISIBLE
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                progress.visibility=View.INVISIBLE

                //To switch to NavigatorActivity if user logs in successfully
                activity?.let {
                    startActivity(Intent(it,NavigatorActivity::class.java))
                    it.finish()
                }

                //To display a warning message on the screen if an error occurs during the user login process
            }.addOnFailureListener {
                progress.visibility=View.INVISIBLE
                view.warningSnackBar("E-mail or password is incorrect")
            }
        } else {
            view.warningSnackBar("Please fill the blanks")
        }

    }

}