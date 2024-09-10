package com.example.expensetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.data.ExpenseDatabase
import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.data.model.ExpenseSummary
import com.example.expensetracker.utils.Util
import com.github.mikephil.charting.data.Entry

class StatViewModel(val expenseDao: ExpenseDao) : ViewModel() {

    val entries = expenseDao.getAllExpenseByDate()
    val topEntry  = expenseDao.getTopExpense()

    fun getEntriesForChart(entries:List<ExpenseSummary>):List<Entry>{
        val list  = mutableListOf<Entry>()
        for (entry in entries){
            val formattedDate = Util.getMilliFromDate(entry.date)
            list.add(Entry(formattedDate.toFloat(),entry.total_amount.toFloat()))
        }
        return list
    }


}


class StatViewModelFactory(private val context: Context):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatViewModel::class.java)){
            val dao = ExpenseDatabase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return StatViewModel(dao) as T
        }
    throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}











