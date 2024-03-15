package com.elifgulay.cookbook.data.dataSource

import android.view.View
import android.widget.ProgressBar
import androidx.navigation.Navigation
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.data.entity.CategoryAnswer
import com.elifgulay.cookbook.data.entity.CategoryBtnAnswer
import com.elifgulay.cookbook.data.entity.FavoriteMeal
import com.elifgulay.cookbook.data.entity.Meals
import com.elifgulay.cookbook.data.entity.MyRecipeProp
import com.elifgulay.cookbook.data.entity.RandomAnswer
import com.elifgulay.cookbook.retrofit.MealsRetroDao
import com.elifgulay.cookbook.room.MealsRoomDao
import com.elifgulay.cookbook.util.go
import com.elifgulay.cookbook.util.successSnackBar
import com.elifgulay.cookbook.util.warningSnackBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CookDataSource(var mealsRetroDao:MealsRetroDao,var mealsRoomDao: MealsRoomDao) {
    suspend fun addFavorite(favoriteMeal: FavoriteMeal){
        mealsRoomDao.addFavorite(favoriteMeal)
    }

    suspend fun deleteFavorite(foodName: String){
        mealsRoomDao.deleteFavorite(foodName)
    }
    suspend fun getFavoriteMeals():List<FavoriteMeal> =
        withContext(Dispatchers.IO){
            return@withContext mealsRoomDao.getFavoriteMeals()
        }

    suspend fun getRandom():RandomAnswer =
        withContext(Dispatchers.IO){
            return@withContext mealsRetroDao.getRandom()
        }

    suspend fun getMeal(foodName:String):RandomAnswer =
    withContext(Dispatchers.IO){
        return@withContext mealsRetroDao.getMeal(foodName)
    }


    suspend fun getCategory(category:String):CategoryAnswer =
        withContext(Dispatchers.IO){
            return@withContext mealsRetroDao.getCategory(category)
        }

    suspend fun getCategoryButtons():CategoryBtnAnswer =
        withContext(Dispatchers.IO){
            return@withContext mealsRetroDao.getCategoryButtons()
        }

    suspend fun addMyRecipe(myRecipeProp: MyRecipeProp){
        mealsRoomDao.addMyRecipe(myRecipeProp)
    }

    suspend fun getMyRecipeList():List<MyRecipeProp> =
        withContext(Dispatchers.IO){
           return@withContext mealsRoomDao.getMyRecipe()
        }

    suspend fun deleteMyRecipe(myRecipeProp: MyRecipeProp){
        mealsRoomDao.deleteMyRecipe(myRecipeProp)
    }

}