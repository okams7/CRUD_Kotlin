package com.voidbit.crud.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.voidbit.crud.nav.NavScreen

@Composable
fun MainScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // get user data Button
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = NavScreen.GetDataScreen.route)
            }
        ) {
            Text(text = "Get User Data")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = NavScreen.GetAllUsersScreen.route)
            }
        ) {
            Text(text = "Get all users")
        }

        // add user data Button
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = NavScreen.AddDataScreen.route)
            }
        ) {
            Text(text = "Add User Data")
        }
    }
}