package com.elifgulay.cookbook.ui.fragment.userFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.databinding.FragmentForgotBinding
import com.elifgulay.cookbook.util.successSnackBar
import com.elifgulay.cookbook.util.warningSnackBar
import com.google.firebase.auth.FirebaseAuth


class ForgotFragment : Fragment() {
    private lateinit var binding:FragmentForgotBinding
    private lateinit var auth:FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Bind xml and fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_forgot,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth=FirebaseAuth.getInstance()
        binding.forgotObject=this

    }

    fun sendEmail(email:String,view: View){
        if (!email.isNullOrEmpty()){
            auth.sendPasswordResetEmail(email).addOnSuccessListener {
                view.successSnackBar("Email has been sent successfully")
            }.addOnFailureListener {
                view.warningSnackBar("Please check your email")
            }
        }
    }

}