package com.voidbit.crud.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.voidbit.crud.screens.AddDataScreen
import com.voidbit.crud.screens.GetAllUsersScreen
import com.voidbit.crud.screens.GetDataScreen
import com.voidbit.crud.screens.MainScreen
import com.voidbit.crud.view_models.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.MainScreen.route,
        modifier = modifier
    ) {
        // main screen
        composable(
            route = NavScreen.MainScreen.route
        ) {
            MainScreen(
                navController = navController,
            )
        }
        // get all users data screen
        composable(
            route = NavScreen.GetAllUsersScreen.route
        ) {
            GetAllUsersScreen()
        }
        // get data screen
        composable(
            route = NavScreen.GetDataScreen.route
        ) {
            GetDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
        // add data screen
        composable(
            route = NavScreen.AddDataScreen.route
        ) {
            AddDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}