package com.elifgulay.cookbook.ui.viewModel.helperViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifgulay.cookbook.data.entity.MyRecipeProp
import com.elifgulay.cookbook.data.repository.CookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecipeShowViewModel @Inject constructor(var cookRepository: CookRepository):ViewModel() {
    var myRecipeList=MutableLiveData<List<MyRecipeProp>>()
    init {
        getMyRecipeList()
    }
    //To retrieve data from the MyRecipe table
    fun getMyRecipeList(){
        CoroutineScope(Dispatchers.Main).launch {
            myRecipeList.value=cookRepository.getMyRecipeList()
        }
    }
    //To delete data in the MyRecipe table
    fun deleteMyRecipe(myRecipeProp: MyRecipeProp){
        CoroutineScope(Dispatchers.Main).launch {
            cookRepository.deleteMyRecipeList(myRecipeProp)
            getMyRecipeList()
        }
    }
}