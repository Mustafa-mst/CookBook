package com.elifgulay.cookbook.data.repository

import android.view.View
import android.widget.ProgressBar
import com.elifgulay.cookbook.data.dataSource.CookDataSource
import com.elifgulay.cookbook.data.entity.CategoryAnswer
import com.elifgulay.cookbook.data.entity.CategoryBtnAnswer
import com.elifgulay.cookbook.data.entity.FavoriteMeal
import com.elifgulay.cookbook.data.entity.Meals
import com.elifgulay.cookbook.data.entity.MyRecipeProp
import com.elifgulay.cookbook.data.entity.RandomAnswer
import com.google.firebase.auth.FirebaseAuth

class CookRepository(var cookDataSource: CookDataSource) {
    suspend fun addFavorite(favoriteMeal: FavoriteMeal)=cookDataSource.addFavorite(favoriteMeal)
    suspend fun deleteFavorite(foodName: String)=cookDataSource.deleteFavorite(foodName)
    suspend fun getFavoriteMeal()=cookDataSource.getFavoriteMeals()
    suspend fun getRandom():RandomAnswer =cookDataSource.getRandom()
    suspend fun getMeal(foodName:String):RandomAnswer =cookDataSource.getMeal(foodName)
    suspend fun getCategory(category:String):CategoryAnswer =cookDataSource.getCategory(category)
    suspend fun getCategoryButtons():CategoryBtnAnswer =cookDataSource.getCategoryButtons()
    suspend fun addMyRecipe(myRecipeProp: MyRecipeProp)=cookDataSource.addMyRecipe(myRecipeProp)
    suspend fun getMyRecipeList()=cookDataSource.getMyRecipeList()
    suspend fun deleteMyRecipeList(myRecipeProp: MyRecipeProp)=cookDataSource.deleteMyRecipe(myRecipeProp)
}