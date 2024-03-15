package com.elifgulay.cookbook.ui.viewModel.mainViewModel

import android.view.View
import android.widget.CheckBox
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
class RandomFragmentViewModel @Inject constructor(var cookRepository: CookRepository):ViewModel() {
    var randomObject= MutableLiveData<Meals>()
    var isChecked=MutableLiveData<Boolean>()

   // To get data from the favorites table
    fun addFavorite(favoriteMeal: FavoriteMeal){
        CoroutineScope(Dispatchers.Main).launch {
            cookRepository.addFavorite(favoriteMeal)
        }
    }

    //To delete data in the favorites table
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
                }else{
                    isChecked.value=false
                }
            }

        }
    }

    //To get random recipe data from API
    fun getRandom(view: View){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                randomObject.value=cookRepository.getRandom().meals!!.get(0)
            }catch (e:Exception){
                view.warningSnackBar("Please check your internet connection")
            }

        }
    }
}