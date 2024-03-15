package com.elifgulay.cookbook.ui.fragment.helperFragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.databinding.FragmentMyRecipeDetailBinding


class MyRecipeDetail : Fragment() {
private lateinit var binding:FragmentMyRecipeDetailBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_my_recipe_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //To access the details of the recipes on the MyRecipe screen
        arguments?.let {
            var myRecipe=MyRecipeDetailArgs.fromBundle(it).myRecipe
           binding.detailImg.setImageBitmap(convertBitmap(myRecipe.recipeImg))
            binding.myRecipeObject=myRecipe
        }
    }
    //To convert the byte array from MyRecipe into an image
    private fun convertBitmap(byteArray:ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }

}