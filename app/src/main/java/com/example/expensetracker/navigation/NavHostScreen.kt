package com.example.expensetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.screen.AddExpense
import com.example.expensetracker.screen.HomeScreen

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "/home") {
        composable("/home") {
            HomeScreen(navController)
        }
        composable("/add") {
            AddExpense(navController)
        }
    }
}














