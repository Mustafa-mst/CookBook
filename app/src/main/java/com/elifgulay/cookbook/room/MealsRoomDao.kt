package com.elifgulay.cookbook.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.elifgulay.cookbook.data.entity.FavoriteMeal
import com.elifgulay.cookbook.data.entity.Meals
import com.elifgulay.cookbook.data.entity.MyRecipeProp

@Dao
interface MealsRoomDao {
    @Query("SELECT * FROM favoriteMeal   ORDER BY strMeal ASC")
    suspend fun getFavoriteMeals():List<FavoriteMeal>

    @Insert
    suspend fun addFavorite(favoriteMeal: FavoriteMeal)

    @Query("DELETE FROM favoriteMeal WHERE strMeal= :mealName")
    suspend fun deleteFavorite(mealName:String)

    @Query("SELECT * FROM myRecipe ORDER BY recipeName ASC")
    suspend fun getMyRecipe():List<MyRecipeProp>

    @Insert
    suspend fun addMyRecipe(myRecipeProp: MyRecipeProp)

    @Delete
    suspend fun deleteMyRecipe(myRecipeProp: MyRecipeProp)
}