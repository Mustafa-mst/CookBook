package com.elifgulay.cookbook.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "myRecipe")
data class MyRecipeProp(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id:Int,
    @ColumnInfo(name = "image")
    var recipeImg:ByteArray,
    @ColumnInfo(name = "recipeName")
    var recipeName:String,
    @ColumnInfo(name = "ingredients")
    var ingredients:String,
    @ColumnInfo(name = "measurements")
    var measurements:String,
    @ColumnInfo(name = "instructions")
    var instructions:String,

):Serializable {
}