package com.elifgulay.cookbook.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.data.entity.MyRecipeProp
import com.elifgulay.cookbook.databinding.MyRecipesRcyBinding
import com.elifgulay.cookbook.ui.fragment.bottomNavFragments.MyRecipeFragmentDirections
import com.elifgulay.cookbook.ui.viewModel.helperViewModel.MyRecipeShowViewModel
import com.google.android.material.snackbar.Snackbar

class MyRecipeAdapter(var mContext: Context, var myRecipeList:List<MyRecipeProp>,var viewModel: MyRecipeShowViewModel):RecyclerView.Adapter<MyRecipeAdapter.MyrecipeVh>() {
    class MyrecipeVh(val binding:MyRecipesRcyBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyrecipeVh {
        val binding:MyRecipesRcyBinding=DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.my_recipes_rcy,parent,false)
        return MyrecipeVh(binding)
    }

    override fun getItemCount(): Int {
        return myRecipeList.size
    }

    override fun onBindViewHolder(holder: MyrecipeVh, position: Int) {
        var myRecipe=myRecipeList.get(position)
        holder.binding.myRecipesFoodName.text=myRecipe.recipeName
        holder.binding.ingTxt.text=myRecipe.ingredients

        holder.binding.myRecipesImg.setImageBitmap(convertBitmap(myRecipe.recipeImg))

        //Action to be taken when clicking on item
        holder.itemView.setOnClickListener {
            val action=MyRecipeFragmentDirections.actionMyRecipeFragmentToMyRecipeDetail(myRecipe)
            Navigation.findNavController(it).navigate(action)
        }

        //To delete recipe when pressing trash button
        holder.binding.myRecipesDeleteImg.setOnClickListener {
            Snackbar.make(it,"Are you sure you want to delete the recipe?",
                Snackbar.LENGTH_LONG).setAction("Yes",object : View.OnClickListener {
                override fun onClick(v: View?) {
                    viewModel.deleteMyRecipe(myRecipe)
                }
            }).show()

        }
    }
    private fun convertBitmap(byteArray:ByteArray): Bitmap {
      return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }
}