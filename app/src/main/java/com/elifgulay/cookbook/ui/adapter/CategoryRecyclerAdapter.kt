package com.elifgulay.cookbook.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.data.entity.CategoryProp
import com.elifgulay.cookbook.databinding.RecyclerOfCategoriesBinding
import com.elifgulay.cookbook.ui.fragment.bottomNavFragments.HomeFragmentDirections
import com.elifgulay.cookbook.util.downLoadUrl

class CategoryRecyclerAdapter(var mContext: Context,var categoryList:List<CategoryProp>):RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryVh>() {
    class CategoryVh(val binding:RecyclerOfCategoriesBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVh {
        val binding:RecyclerOfCategoriesBinding=DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.recycler_of_categories,parent,false)
        return CategoryVh(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryVh, position: Int) {
        holder.binding.recyclerText.text=categoryList.get(position).strMeal
        holder.binding.recyclerImg.downLoadUrl(categoryList.get(position).strMealThumb)

        //To send the name of the clicked recipe to the detail
        holder.itemView.setOnClickListener {
            val action=HomeFragmentDirections.homeToDetail(categoryList.get(position).strMeal)
            Navigation.findNavController(it).navigate(action)
        }
    }
}