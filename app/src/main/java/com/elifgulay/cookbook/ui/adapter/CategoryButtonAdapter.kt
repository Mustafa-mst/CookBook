package com.elifgulay.cookbook.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.data.entity.CategoryBtnProp
import com.elifgulay.cookbook.databinding.RecyclerOfCategoriesButtonsBinding
import com.elifgulay.cookbook.ui.viewModel.mainViewModel.HomeViewModel

class CategoryButtonAdapter(val mContext: Context, var buttonList:List<CategoryBtnProp>,var viewModel: HomeViewModel):RecyclerView.Adapter<CategoryButtonAdapter.CategoryBtnVh>() {
    private var selectedItemPos = -1
    private var lastItemSelectedPos = -1
    class CategoryBtnVh(val binding:RecyclerOfCategoriesButtonsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryBtnVh {
        val binding:RecyclerOfCategoriesButtonsBinding=DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.recycler_of_categories_buttons,parent,false)
        return CategoryBtnVh(binding)
    }

    override fun getItemCount(): Int {
        return buttonList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoryBtnVh, position: Int) {
        var category=buttonList.get(position)
       holder.binding.categoryRcyBtn.text=category.strCategory

        //To get data based on the clicked category
        holder.binding.categoryRcyBtn.setOnClickListener {
            viewModel.getCategory(category.strCategory)
            selectedItemPos=holder.adapterPosition
        }

    }
}