package com.elifgulay.cookbook.util

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.ui.fragment.helperFragments.RecipeAddFragmentDirections
import com.google.android.material.snackbar.Snackbar
import java.net.URL

fun View.warningSnackBar(message:String){
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.warning))
        .setTextColor(getResources().getColor(R.color.warninText)).show()
}

fun View.successSnackBar(message:String){
    Snackbar.make(this,message,Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.splash_button_color))
        .setTextColor(getResources().getColor(R.color.white)).show()
}

fun Navigation.go(view: View,id:Int){
    this.findNavController(view).navigate(id)
}

fun ImageView.downLoadUrl(url:String?){
    try {
        val progress=CircularProgressDrawable(this.context).apply {
            strokeWidth=8f
            centerRadius=40f
            start()
        }
        Glide.with(this).load(url).placeholder(progress).into(this)
    }catch (e:Exception){}


}
