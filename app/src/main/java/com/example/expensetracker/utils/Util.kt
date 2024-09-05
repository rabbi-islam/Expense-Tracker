package com.example.expensetracker.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Locale

object Util {
    fun formatDateToHumanReadableForm(date:Long):String{
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormatter.format(date)
    }

    @SuppressLint("DefaultLocale")
    fun formatToDecimalValue(value:Double):String{
        return String.format("%.2f",value)
    }
}