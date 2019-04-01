package com.example.expensetracker.util;

import com.example.expensetracker.model.Category;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class CategoryDiffCallback extends DiffUtil.Callback {
    private List<Category> oldCategoryList;
    private List<Category> newCategoryList;

    public CategoryDiffCallback(List<Category> oldCategoryList, List<Category> newCategoryList){
        this.oldCategoryList = oldCategoryList;
        this.newCategoryList = newCategoryList;
    }

    @Override
    public int getOldListSize() {
        return oldCategoryList.size();
    }

    @Override
    public int getNewListSize() {
        return newCategoryList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Category oldCategory = oldCategoryList.get(oldItemPosition);
        Category newCategory = newCategoryList.get(newItemPosition);
        return oldCategory.getName().equals(newCategory.getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Category oldCategory = oldCategoryList.get(oldItemPosition);
        Category newCategory = newCategoryList.get(newItemPosition);
        return oldCategory.getName().equals(newCategory.getName());
    }
}
