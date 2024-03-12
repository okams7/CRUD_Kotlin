package com.voidbit.crud.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.voidbit.crud.models.UserData
import com.voidbit.crud.view_models.SharedViewModel

@Composable
fun GetAllUsersScreen(){
    val viewModel: SharedViewModel = viewModel()
    val context = LocalContext.current
    viewModel.getAllUsers(context = context)

    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 6.dp
        ),
//        userScrollEnabled = false,
        modifier = Modifier
//            .fillMaxHeight()
//            .verticalScroll(state = rememberScrollState())
                            .height(500.dp)
    ) {
        items(viewModel.users.value) {
            UserItem(user = it, modifier = Modifier.padding(6.dp).fillMaxWidth())
        }
    }
}

@Composable
fun UserItem(user: UserData, modifier: Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Column(modifier = modifier) {
                Text(text = user.name?:"", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                    Text(text = user.userID?:"", fontSize = 20.sp)
                }

            }
        }
    }
}

@Preview
@Composable
fun ShowPreview(){
    GetAllUsersScreen()
}