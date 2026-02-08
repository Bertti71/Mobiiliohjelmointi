package com.example.taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.taskapp.navigation.Routes
import com.example.taskapp.ui.theme.TaskAppTheme
import com.example.taskapp.view.CalendarScreen
import com.example.taskapp.view.HomeScreen
import com.example.taskapp.view.SettingsScreen
import com.example.taskapp.viewmodel.TaskViewModel



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TaskAppTheme {
                val navController = rememberNavController()
                val vm: TaskViewModel = viewModel()

                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentRoute == Routes.HOME,
                                onClick = { navController.navigate(Routes.HOME) { launchSingleTop = true } },
                                label = { Text("Home") },
                                icon = {}
                            )
                            NavigationBarItem(
                                selected = currentRoute == Routes.CALENDAR,
                                onClick = { navController.navigate(Routes.CALENDAR) { launchSingleTop = true } },
                                label = { Text("Calendar") },
                                icon = {}
                            )
                            NavigationBarItem(
                                selected = currentRoute == Routes.SETTINGS,
                                onClick = { navController.navigate(Routes.SETTINGS) { launchSingleTop = true } },
                                label = { Text("Settings") },
                                icon = {}
                            )
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HOME
                    ) {
                        composable(Routes.HOME) { HomeScreen(vm = vm) }
                        composable(Routes.CALENDAR) { CalendarScreen(vm = vm) }
                        composable(Routes.SETTINGS) { SettingsScreen() }
                    }
                }
            }
        }
    }
}
