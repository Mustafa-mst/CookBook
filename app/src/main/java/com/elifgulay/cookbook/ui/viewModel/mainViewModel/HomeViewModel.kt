package com.elifgulay.cookbook.ui.viewModel.mainViewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifgulay.cookbook.data.entity.CategoryAnswer
import com.elifgulay.cookbook.data.entity.CategoryBtnAnswer
import com.elifgulay.cookbook.data.entity.CategoryBtnProp
import com.elifgulay.cookbook.data.entity.CategoryProp
import com.elifgulay.cookbook.data.entity.Meals
import com.elifgulay.cookbook.data.repository.CookRepository
import com.elifgulay.cookbook.util.warningSnackBar
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var cookRepository: CookRepository):ViewModel() {
    var mealList=MutableLiveData<List<CategoryProp>>()
    var categoryBtnList=MutableLiveData<List<CategoryBtnProp>>()
    private var searchMeal=ArrayList<CategoryProp>()


    init {
        getCategoryButtons()
        getCategory("Beef")
    }

    //To perform a search on the API when a letter or word is entered
    fun search(name:String,view: View) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                cookRepository.getMeal(name).meals?.let {
                    searchMeal.clear()
                    it.forEach {
                        if (it.strMeal!=null&&it.strMealThumb!=null){
                            searchMeal.add(CategoryProp(it.strMeal!!, it.strMealThumb!!))
                        }
                    }
                    mealList.value = searchMeal
                }
            }catch (e:Exception){
                view.warningSnackBar("Please check your internet connection")
            }

        }
    }

    //To get recipe list by category from API
    fun getCategory(category:String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                mealList.value= cookRepository.getCategory(category).meals
            }catch (e:Exception){

            }

        }
    }

    //To get categories from API
    fun getCategoryButtons(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                categoryBtnList.value=cookRepository.getCategoryButtons().categories
            }catch (e:Exception){
            }

        }
    }



}