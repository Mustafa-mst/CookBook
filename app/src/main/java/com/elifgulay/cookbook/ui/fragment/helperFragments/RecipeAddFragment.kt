package com.elifgulay.cookbook.ui.fragment.helperFragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.data.entity.MyRecipeProp
import com.elifgulay.cookbook.databinding.FragmentRecipeAddBinding
import com.elifgulay.cookbook.ui.viewModel.helperViewModel.MyRecipeAddViewModel
import com.elifgulay.cookbook.util.go
import com.elifgulay.cookbook.util.warningSnackBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class RecipeAddFragment : Fragment() {
private lateinit var binding:FragmentRecipeAddBinding
private lateinit var request:ActivityResultLauncher<String>
private lateinit var cameraPicker:ActivityResultLauncher<Intent>
private lateinit var viewModel: MyRecipeAddViewModel
private  var bitmap: Bitmap?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Bind xml and fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_recipe_add, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(MyRecipeAddViewModel::class.java)
        binding.addObject=this
        registerLauncher()
    }
    //To add the new recipe created to the MyRecipe table
    fun addMyRecipe(recipeName:String,ingredients:String,measurements:String,instructions:String){
        if (bitmap!=null&&!recipeName.isNullOrEmpty()&& !ingredients.isNullOrEmpty()&& !measurements.isNullOrEmpty()&& !instructions.isNullOrEmpty()){
            val outputStream=ByteArrayOutputStream()
            bitmap!!.compress(Bitmap.CompressFormat.PNG,100,outputStream)
            viewModel.addMyRecipe(MyRecipeProp(0,outputStream.toByteArray(),recipeName,ingredients,measurements,instructions))

            Navigation.go(requireView(),R.id.recipeAddToMyRecipe)
        }else{
            requireView().warningSnackBar("Please fill the blanks")
        }



    }

    //Permission to access the camera and the method by which its action is performed
    fun shotPhoto(){
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),android.Manifest.permission.CAMERA)){
                Snackbar.make(requireView(),"Permission Required!",Snackbar.LENGTH_LONG).setAction("Give Permission") {
                    request.launch(android.Manifest.permission.CAMERA)
                }.show()
            }else{
                request.launch(android.Manifest.permission.CAMERA)
            }
        }else{
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraPicker.launch(intent)
        }
    }

    private fun registerLauncher(){
       // Method that will run if the user gives access to the camera
        cameraPicker=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode== Activity.RESULT_OK){
                it.data?.let {
                    bitmap=it.getParcelableExtra<Bitmap>("data")
                    bitmap?.let {
                        binding.cameraImg.setImageBitmap(bitmap)
                        binding.cameraImg.scaleType=ImageView.ScaleType.CENTER_CROP
                    }

                }
            }
        }
        //To get permission to access the camera from the user
        request=registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if (it){
                val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraPicker.launch(intent)
            }else{
                Toast.makeText(requireContext(),"Permission Denied!",Toast.LENGTH_LONG).show()
            }
        }
    }

}