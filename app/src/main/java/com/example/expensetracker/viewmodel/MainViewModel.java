package com.example.expensetracker.viewmodel;


import com.example.expensetracker.model.Category;
import com.example.expensetracker.model.Expense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Expense>> expenseLiveData;
    private MutableLiveData<List<Category>> categoryLiveData;

    private List<Expense> expenseList;
    private List<Category> categoryList;

    public MainViewModel(){
        this.expenseLiveData = new MutableLiveData<>();
        this.categoryLiveData = new MutableLiveData<>();

        this.expenseList = new ArrayList<>();
        this.categoryList = new ArrayList<>();

    }

    public void addExpense(Expense expense){
        this.expenseList.add(expense);
        this.expenseLiveData.setValue(expenseList);
        this.updateCategoryPrices(expense.getCategory(),expense.getPrice(),true);
    }

    public void addCategory(Category category){
        this.categoryList.add(category);
        this.categoryLiveData.setValue(categoryList);
    }

    public void removeExpense(Expense expense){
        this.expenseList.remove(expense);
        this.expenseLiveData.setValue(expenseList);
        this.updateCategoryPrices(expense.getCategory(),expense.getPrice(),false);
    }

    public void filterExpenses(String input,String category){
        List<Expense> filterExpenses = new ArrayList<>();
        for(Expense exp: expenseList){
            if(exp.getName().toLowerCase().startsWith(input.toLowerCase())) {
                if (category.equals("All")) {
                    filterExpenses.add(exp);
                }   else if(exp.getCategory().equals(category)){
                    filterExpenses.add(exp);
                }
            }
        }
        this.sortExpenses(filterExpenses);
        this.expenseLiveData.setValue(filterExpenses);
    }

    public void updateCategoryPrices(String category,int price,boolean bool){
        List<Category> updatedCategory = new ArrayList<>();
        for(Category cat: categoryList) {
            if (cat.getName().equals(category)) {
                cat.updatePrice(price, bool);
            }
            updatedCategory.add(cat);
        }
        this.sortCategories(updatedCategory);
        this.categoryLiveData.setValue(updatedCategory);
    }

    public void sortCategories(List<Category> categories){
        Collections.sort(categories, new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                return Integer.compare(c2.getPrice(),c1.getPrice());
            }
        });
    }

    public void sortCategoriesLenght(List<Category> categories){
        Collections.sort(categories, new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                return Integer.compare(c1.getName().length(),c2.getName().length());
            }
        });
    }


    public void sortExpenses(List<Expense> expenses){
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense e1, Expense e2) {
                return Integer.compare(e2.getPrice(),e1.getPrice());
            }
        });
    }

    public LiveData<List<Expense>> getExpenseLiveData() {
        return expenseLiveData;
    }

    public MutableLiveData<List<Category>> getCategoryLiveData() {
        return categoryLiveData;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    private void generateDummyData(){
        for(int i=0;i<expenses.length;i++){
            if(i<3) {
                this.addCategory(new Category(categories[i], 0));
                this.addExpense(new Expense(expenses[i],categories[0],expensesPrice[i]));
            } else
                this.addExpense(new Expense(expenses[i],categories[1],expensesPrice[i]));
        }
    }

    private String[] categories = {"Groceries","Bills","Tuition"};
    private String[] expenses = {"Milk","Butter","Cookies","SBB","Telenor"};
    private int[] expensesPrice = {80,100,150,2500,999};
}
