package com.example.taskapp.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.taskapp.navigation.Routes
import com.example.taskapp.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navController: NavController,
    vm: TaskViewModel
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (currentRoute) {
                            Routes.HOME -> "Tasks"
                            Routes.CALENDAR -> "Calendar"
                            Routes.SETTINGS -> "Settings"
                            else -> "TaskApp"
                        }
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == Routes.HOME,
                    onClick = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.HOME)
                            launchSingleTop = true
                        }
                    },
                    label = { Text("Home") },
                    icon = {}
                )
                NavigationBarItem(
                    selected = currentRoute == Routes.CALENDAR,
                    onClick = {
                        navController.navigate(Routes.CALENDAR) {
                            popUpTo(Routes.HOME)
                            launchSingleTop = true
                        }
                    },
                    label = { Text("Calendar") },
                    icon = {}
                )
                NavigationBarItem(
                    selected = currentRoute == Routes.SETTINGS,
                    onClick = {
                        navController.navigate(Routes.SETTINGS) {
                            popUpTo(Routes.HOME)
                            launchSingleTop = true
                        }
                    },
                    label = { Text("Settings") },
                    icon = {}
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController as NavHostController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                HomeScreen(vm = vm)
            }
            composable(Routes.CALENDAR) {
                CalendarScreen(vm = vm)
            }
            composable(Routes.SETTINGS) {
                SettingsScreen()
            }
        }
    }
}
