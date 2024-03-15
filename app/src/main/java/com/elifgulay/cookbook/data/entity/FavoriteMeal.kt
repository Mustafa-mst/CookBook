package com.elifgulay.cookbook.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favoriteMeal")
data class FavoriteMeal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "meal_id")
    var meal_id:Int,
    @ColumnInfo("strMeal")
    var strMeal:String,
    @ColumnInfo(name = "strCategory")
    var strCategory:String,
    @ColumnInfo(name = "strArea")
    var strArea:String,
    @ColumnInfo(name = "strInstructions")
    var strInstructions:String,
    @ColumnInfo(name = "strMealThumb")
    var strMealThumb:String,
    @ColumnInfo(name = "strYoutube")
    var strYoutube:String,
    @ColumnInfo(name = "strIngredient1")
    var strIngredient1:String,
    @ColumnInfo(name = "strIngredient2")
    var strIngredient2:String,
    @ColumnInfo(name = "strIngredient3")
    var strIngredient3:String,
    @ColumnInfo(name = "strIngredient4")
    var strIngredient4:String,
    @ColumnInfo(name = "strIngredient5")
    var strIngredient5:String,
    @ColumnInfo(name = "strIngredient6")
    var strIngredient6:String,
    @ColumnInfo(name = "strIngredient7")
    var strIngredient7:String,
    @ColumnInfo(name = "strMeasure1")
    var strMeasure1:String,
    @ColumnInfo(name = "strMeasure2")
    var strMeasure2:String,
    @ColumnInfo(name = "strMeasure3")
    var strMeasure3:String,
    @ColumnInfo(name = "strMeasure4")
    var strMeasure4:String,
    @ColumnInfo(name = "strMeasure5")
    var strMeasure5:String,
    @ColumnInfo(name = "strMeasure6")
    var strMeasure6:String,
    @ColumnInfo(name = "strMeasure7")
    var strMeasure7:String

): Serializable {
}