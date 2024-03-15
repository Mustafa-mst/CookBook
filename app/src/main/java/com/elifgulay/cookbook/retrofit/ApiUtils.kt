package com.elifgulay.cookbook.retrofit

class ApiUtils {
    companion object{
        val BASE_URL="https://www.themealdb.com/api/json/v1/1/"
        fun getDao():MealsRetroDao{
            return RetrofitClient.getClient(BASE_URL).create(MealsRetroDao::class.java)
        }
    }
}