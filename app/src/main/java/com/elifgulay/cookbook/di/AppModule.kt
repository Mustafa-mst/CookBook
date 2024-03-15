package com.elifgulay.cookbook.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elifgulay.cookbook.data.dataSource.CookDataSource
import com.elifgulay.cookbook.data.repository.CookRepository
import com.elifgulay.cookbook.retrofit.ApiUtils
import com.elifgulay.cookbook.retrofit.MealsRetroDao
import com.elifgulay.cookbook.room.MealsDatabase
import com.elifgulay.cookbook.room.MealsRoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCookDataSource(mealsRetroDao: MealsRetroDao,mealsRoomDao: MealsRoomDao): CookDataSource {
        return CookDataSource(mealsRetroDao,mealsRoomDao)
    }

    @Provides
    @Singleton
    fun provideCookRepository(cookDataSource: CookDataSource): CookRepository {
        return CookRepository(cookDataSource)
    }

    @Provides
    @Singleton
    fun provideMealsRetroDao():MealsRetroDao{
        return ApiUtils.getDao()
    }

    @Provides
    @Singleton
    fun provideMealsDao(@ApplicationContext context:Context):MealsRoomDao{
        var database=Room.databaseBuilder(context,MealsDatabase::class.java,"Database").build()
        return database.getMealsDao()
    }



}