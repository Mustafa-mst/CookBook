package com.elifgulay.cookbook.ui.fragment.userFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.databinding.FragmentSignUpBinding
import com.elifgulay.cookbook.util.go
import com.elifgulay.cookbook.util.successSnackBar
import com.elifgulay.cookbook.util.warningSnackBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding:FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Bind xml and fragment
        binding=DataBindingUtil.inflate(layoutInflater,R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpObject=this
        binding.auth= FirebaseAuth.getInstance()
    }

    //The method that will run when the user tries to sign up
     fun signUp(name: String, email: String, password: String, rePassword: String, auth: FirebaseAuth, view: View, progress: ProgressBar) {
        if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty() && !rePassword.isNullOrEmpty()) {

            if (password.equals(rePassword)) {
                progress.visibility=View.VISIBLE
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    view.successSnackBar("Your account has been created successfully")
                    progress.visibility=View.INVISIBLE
                    //to add user name to firebase auth
                    auth.currentUser!!.updateProfile(userProfileChangeRequest {
                        displayName = name
                    })
                    //To go to the sign in screen after signed up
                    Navigation.go(view, R.id.signUpToSignIn)

                }.addOnFailureListener {
                    progress.visibility=View.INVISIBLE
                    view.warningSnackBar("Somethings happened wrong")
                }
            } else {
                view.warningSnackBar("Passwords aren't matching")

            }
        } else {
            view.warningSnackBar("Please fill the blanks")
        }

    }


}