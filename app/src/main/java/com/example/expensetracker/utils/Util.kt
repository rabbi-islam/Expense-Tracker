package com.example.expensetracker.utils

import android.annotation.SuppressLint
import com.example.expensetracker.R
import com.example.expensetracker.data.model.ExpenseEntity
import java.text.SimpleDateFormat
import java.util.Locale
import java.text.ParseException
import java.util.Date

object Util {
    fun formatDateToHumanReadableForm(date:Long):String{
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormatter.format(date)
    }

    fun formatDateForChart(date:Long):String{
        val dateFormatter = SimpleDateFormat("dd-MMM", Locale.getDefault())
        return dateFormatter.format(date)
    }

    @SuppressLint("DefaultLocale")
    fun formatToDecimalValue(value:Double):String{
        return String.format("%.2f",value)
    }


    fun getMillisFromDate(date: String): Long {
        return getMilliFromDate(date)
    }

    fun getMilliFromDate(dateFormat: String?): Long {
        var date = Date()
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        try {
            date = formatter.parse(dateFormat)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        println("Today is $date")
        return date.time
    }

    fun getItemIcon(item: ExpenseEntity):Int{
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