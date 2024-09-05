package com.example.expensetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.R
import com.example.expensetracker.data.ExpenseDatabase
import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.data.model.ExpenseEntity
import com.example.expensetracker.utils.Util

class HomeViewModel(expenseDao: ExpenseDao) : ViewModel() {
    val expenses = expenseDao.getAllExpenses()

    fun getBalance(list: List<ExpenseEntity>): String {
        var balance = 0.0
        for(expense in list){
            if (expense.type == "Income"){
                balance += expense.amount
            }else{
                balance -= expense.amount
            }
        }
        return "$ ${Util.formatToDecimalValue(balance)}"
    }


    fun getTotalExpense(list: List<ExpenseEntity>): String {
        var total = 0.0
        for (expense in list){
                total += expense.amount
        }
        return "$ ${Util.formatToDecimalValue(total)}"
    }

    fun getTotalIncome(list: List<ExpenseEntity>): String {
        var totalIncome = 0.0
        for (expense in list){
            if (expense.type == "Income"){
                totalIncome += expense.amount
            }
        }
        return "$ ${Util.formatToDecimalValue(totalIncome)}"
    }
    fun getItemIcon(item:ExpenseEntity):Int{
        if (item.category == "Paypal") {
            return R.drawable.ic_paypal
        } else if (item.category == "Netflix") {
            return R.drawable.ic_netflix
        }else if (item.category == "Starbucks") {
            return R.drawable.ic_starbucks
        }else
            return R.drawable.ic_upwork

    }
}


class HomeViewModelFactory(private val context: Context):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            val dao = ExpenseDatabase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
    throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}











