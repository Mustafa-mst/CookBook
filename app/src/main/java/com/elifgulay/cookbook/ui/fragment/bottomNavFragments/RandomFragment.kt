package com.elifgulay.cookbook.ui.fragment.bottomNavFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.data.entity.FavoriteMeal
import com.elifgulay.cookbook.databinding.ActivityNavigatorBinding
import com.elifgulay.cookbook.databinding.FragmentRandomBinding
import com.elifgulay.cookbook.ui.fragment.helperFragments.DetailFragmentDirections
import com.elifgulay.cookbook.ui.viewModel.mainViewModel.RandomFragmentViewModel
import com.elifgulay.cookbook.util.downLoadUrl
import com.elifgulay.cookbook.util.successSnackBar
import com.elifgulay.cookbook.util.warningSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomFragment : Fragment() {
    private lateinit var binding: FragmentRandomBinding
    private  var youtube:String?=null
    private lateinit var viewModel:RandomFragmentViewModel
    private var isSelected=false
    private var clickHolder=0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_random,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(RandomFragmentViewModel::class.java)

        //To get random recipe from the viewModel
        viewModel.getRandom(view)
        observeRandom()
    }

    //The method which we work on random prescription data coming from the API
    fun observeRandom() {
        try {
            viewModel.randomObject.observe(viewLifecycleOwner) {
                binding.mealObject = it
                binding.detailImg.downLoadUrl(it.strMealThumb)
                youtube=it.strYoutube
                binding.youtubeIcon.setOnClickListener {
                    navigateInRandomYoutube(it)
                }
                viewModel.compareFavoriteMealClick(it.strMeal!!)
                viewModel.isChecked.observe(viewLifecycleOwner){
                    if (it){
                        isSelected=true
                        clickHolder=1
                        binding.favoriteBtn.setImageResource(R.drawable.favorite_colored)
                    }else{
                        isSelected=false
                        clickHolder=0
                        binding.favoriteBtn.setImageResource(R.drawable.favorite)
                    }
                }
                stateOfFavoriteBtn(it.strMeal!!,it.strCategory,it.strArea,it.strInstructions,it.strMealThumb!!,it.strYoutube,it.strIngredient1,
                    it.strIngredient2,
                    it.strIngredient3,
                    it.strIngredient4,
                    it.strIngredient5,
                    it.strIngredient6,
                    it.strIngredient7,
                    it.strMeasure1,
                    it.strMeasure2,
                    it.strMeasure3,
                    it.strMeasure4,
                    it.strMeasure5,
                    it.strMeasure6,
                    it.strMeasure7)

                ingredients(
                    it.strIngredient1,
                    it.strIngredient2,
                    it.strIngredient3,
                    it.strIngredient4,
                    it.strIngredient5,
                    it.strIngredient6,
                    it.strIngredient7
                )
                measures(
                    it.strMeasure1,
                    it.strMeasure2,
                    it.strMeasure3,
                    it.strMeasure4,
                    it.strMeasure5,
                    it.strMeasure6,
                    it.strMeasure7
                )

            }
        }catch (e:Exception){
            requireView().warningSnackBar("Please check your internet connection ")
        }

    }

    //To check the click action of the favorite button
    private fun stateOfFavoriteBtn(strMeal:String, strCategory:String, strArea:String, strInstructions:String, strMealThumb:String, strYoutube:String, strIngredient1:String,strIngredient2:String, strIngredient3:String, strIngredient4:String, strIngredient5:String, strIngredient6:String, strIngredient7:String, strMeasure1:String, strMeasure2:String, strMeasure3:String, strMeasure4:String, strMeasure5:String, strMeasure6:String, strMeasure7:String){
        binding.favoriteBtn.setOnClickListener {
            if (clickHolder==0){
                isSelected=true
                binding.favoriteBtn.setImageResource(R.drawable.favorite_colored)
                viewModel.addFavorite(FavoriteMeal(0,strMeal, strCategory, strArea, strInstructions, strMealThumb, strYoutube, strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5, strIngredient6, strIngredient7, strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5, strMeasure6, strMeasure7))
                requireView().successSnackBar("Added Successfully ")
                clickHolder++
            }else{
                viewModel.deleteFavorite(strMeal)
                binding.favoriteBtn.setImageResource(R.drawable.favorite)
                requireView().successSnackBar("Removed Successfully")
                clickHolder=0
            }
        }


    }


    //To display the YouTube video of the recipe on the screen when the YouTube icon is pressed
    fun navigateInRandomYoutube(view:View){
        youtube?.let {
            val action= RandomFragmentDirections.randomToYoutube(youtube!!)
            Navigation.findNavController(view).navigate(action)
        }
    }


    //To write the ingredients line by line
    fun ingredients(vararg ingredients: String) {
        var a = ""
        ingredients.forEach {
            a += "$it\n"
        }
        binding.detailIngredientsTxt.text = a
    }

    //To write the measurements line by line
    fun measures(vararg measure: String) {
        var a = ""
        measure.forEach {
            a += "$it\n"
        }
        binding.detailMeasureTxt.text = a
    }


}