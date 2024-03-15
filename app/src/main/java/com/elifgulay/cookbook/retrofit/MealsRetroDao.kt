package com.elifgulay.cookbook.retrofit

import com.elifgulay.cookbook.data.entity.CategoryAnswer
import com.elifgulay.cookbook.data.entity.CategoryBtnAnswer
import com.elifgulay.cookbook.data.entity.Meals
import com.elifgulay.cookbook.data.entity.RandomAnswer
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsRetroDao {

    @GET("filter.php")
    suspend fun getCategory(@Query("c") c:String):CategoryAnswer

    @GET("search.php")
    suspend fun getMeal(@Query("s")s:String):RandomAnswer

    @GET("random.php")
    suspend fun getRandom():RandomAnswer

    @GET("categories.php")
    suspend fun getCategoryButtons():CategoryBtnAnswer
}