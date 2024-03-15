package com.elifgulay.cookbook.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class Meals(
    var strMeal:String?,
    var strCategory:String,
    var strArea:String,
    var strInstructions:String,
    var strMealThumb:String?,
    var strYoutube:String,
    var strIngredient1:String,
    var strIngredient2:String,
    var strIngredient3:String,
    var strIngredient4:String,
    var strIngredient5:String,
    var strIngredient6:String,
    var strIngredient7:String,
    var strMeasure1:String,
    var strMeasure2:String,
    var strMeasure3:String,
    var strMeasure4:String,
    var strMeasure5:String,
    var strMeasure6:String,
    var strMeasure7:String
    ):Serializable
