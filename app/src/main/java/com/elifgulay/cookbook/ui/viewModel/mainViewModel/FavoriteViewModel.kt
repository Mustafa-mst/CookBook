package com.elifgulay.cookbook.ui.viewModel.mainViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifgulay.cookbook.data.entity.FavoriteMeal
import com.elifgulay.cookbook.data.repository.CookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var cookRepository: CookRepository):ViewModel() {
    var favoriteList=MutableLiveData<List<FavoriteMeal>>()

    init {
        try {
            getFavoriteMeals()
        }catch (e:Exception){
        }
    }

    //To get data from the favorites table
    fun getFavoriteMeals(){
        CoroutineScope(Dispatchers.Main).launch {
            favoriteList.value=cookRepository.getFavoriteMeal()
        }
    }

    //To delete data in the favorites table
    fun deleteFavoriteMeal(foodName:String){
        CoroutineScope(Dispatchers.Main).launch {
            cookRepository.deleteFavorite(foodName)
            getFavoriteMeals()
        }
    }
}