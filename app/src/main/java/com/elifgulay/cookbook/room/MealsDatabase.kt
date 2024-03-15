package com.elifgulay.cookbook.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.elifgulay.cookbook.data.entity.FavoriteMeal
import com.elifgulay.cookbook.data.entity.Meals
import com.elifgulay.cookbook.data.entity.MyRecipeProp

@Database(entities = [FavoriteMeal::class,MyRecipeProp::class], version = 4)
abstract class MealsDatabase :RoomDatabase(){
    abstract fun getMealsDao():MealsRoomDao
}