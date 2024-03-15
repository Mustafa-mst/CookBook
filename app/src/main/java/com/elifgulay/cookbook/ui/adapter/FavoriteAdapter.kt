package com.elifgulay.cookbook.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.data.entity.FavoriteMeal
import com.elifgulay.cookbook.data.entity.Meals
import com.elifgulay.cookbook.databinding.FavoriteRcyViewBinding
import com.elifgulay.cookbook.ui.fragment.bottomNavFragments.FavoriteFragmentDirections
import com.elifgulay.cookbook.ui.fragment.bottomNavFragments.HomeFragmentDirections
import com.elifgulay.cookbook.ui.viewModel.mainViewModel.FavoriteViewModel
import com.elifgulay.cookbook.util.downLoadUrl
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter(var mContext: Context, var favoriteList:List<FavoriteMeal>,var viewModel:FavoriteViewModel):RecyclerView.Adapter<FavoriteAdapter.FavoriteVh>() {
    class FavoriteVh(val binding:FavoriteRcyViewBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteVh {
        val binding:FavoriteRcyViewBinding=DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.favorite_rcy_view,parent,false)
        return FavoriteVh(binding)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: FavoriteVh, position: Int) {
        var favoriteMeal=favoriteList.get(position)
        holder.binding.favoriteImg.downLoadUrl(favoriteMeal.strMealThumb)
        holder.binding.favoriteFoodName.text=favoriteMeal.strMeal

        ////To remove recipe when pressing trash button
        holder.binding.deleteImg.setOnClickListener {
            Snackbar.make(it,"Are you sure you want to remove the recipe from favorites?",
                Snackbar.LENGTH_LONG).setAction("Yes",object : View.OnClickListener {
                override fun onClick(v: View?) {
                    viewModel.deleteFavoriteMeal(favoriteMeal.strMeal)
                }
            }).show()

        }

        //To send the recipe name to the detail when the recipe in the favorites is clicked
        holder.itemView.setOnClickListener {
            val action= FavoriteFragmentDirections.favoriteToDetail(favoriteMeal.strMeal)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.ingTxt.text=favoriteMeal.strInstructions
        holder.binding.areaTxt.text=favoriteMeal.strArea
        holder.binding.categoryTxt.text=favoriteMeal.strCategory
    }
}