package com.example.expensetracker.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.R
import com.example.expensetracker.screen.AddExpense
import com.example.expensetracker.screen.HomeScreen
import com.example.expensetracker.screen.StatScreen
import com.example.expensetracker.ui.theme.zinc

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    var bottomBarVisibility by remember { mutableStateOf(true) }
    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = bottomBarVisibility) {
                NavigationBottomBar(
                    navController = navController,
                    items = listOf(
                        NavItem("/home", R.drawable.ic_home),
                        NavItem("/stat", R.drawable.ic_stats)
                    )
                )
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "/home",
            modifier = Modifier.padding(it)
        ) {
            composable("/home") {
                bottomBarVisibility = true
                HomeScreen(navController)
            }
            composable("/add") {
                bottomBarVisibility = false
                AddExpense(navController)
            }
            composable("/stat") {
                bottomBarVisibility = true
                StatScreen(navController)
            }
        }
    }


}


data class NavItem(
    val route: String,
    val icon: Int
)

@Composable
fun NavigationBottomBar(navController: NavController, items: List<NavItem>) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    BottomAppBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = null)
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = zinc,
                    selectedTextColor = zinc,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )

            )
        }
    }

}












