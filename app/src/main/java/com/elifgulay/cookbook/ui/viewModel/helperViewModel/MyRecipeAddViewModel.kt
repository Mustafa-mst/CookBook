package com.elifgulay.cookbook.ui.viewModel.helperViewModel

import androidx.lifecycle.ViewModel
import com.elifgulay.cookbook.data.entity.MyRecipeProp
import com.elifgulay.cookbook.data.repository.CookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecipeAddViewModel @Inject constructor(var cookRepository: CookRepository) :ViewModel() {
    //To add a recipe to the MyRecipe table
    fun addMyRecipe(myRecipeProp:MyRecipeProp){
        CoroutineScope(Dispatchers.Main).launch {
            cookRepository.addMyRecipe(myRecipeProp)
        }
    }
}