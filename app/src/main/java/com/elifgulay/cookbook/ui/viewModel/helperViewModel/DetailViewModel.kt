package com.elifgulay.cookbook.ui.viewModel.helperViewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifgulay.cookbook.data.entity.FavoriteMeal
import com.elifgulay.cookbook.data.entity.Meals
import com.elifgulay.cookbook.data.repository.CookRepository
import com.elifgulay.cookbook.util.warningSnackBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var cookRepository: CookRepository):ViewModel() {
    var detailMeals= MutableLiveData<Meals>()
    var isChecked=MutableLiveData<Boolean>()

    //To add the recipe to favorites when the favorite button is pressed
    fun addFavorite(favoriteMeal: FavoriteMeal){
        CoroutineScope(Dispatchers.Main).launch {
            cookRepository.addFavorite(favoriteMeal)
        }
    }

   //To delete the recipe added to favorites
    fun deleteFavorite(foodName:String){
        CoroutineScope(Dispatchers.Main).launch {
            cookRepository.deleteFavorite(foodName)
        }
    }

    fun compareFavoriteMealClick(foodName:String){
        CoroutineScope(Dispatchers.Main).launch {
           cookRepository.getFavoriteMeal().forEach {
               if (it.strMeal==foodName){
                   isChecked.value=true
               }
           }

        }
    }

    //To get data from API based on food name
    fun getMealFromName(name:String,view: View){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                detailMeals.value=cookRepository.getMeal(name).meals!!.get(0)
            }catch (e:Exception){
                view.warningSnackBar("Please check your internet connection")
            }

        }
    }

}