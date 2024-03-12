package com.voidbit.crud.nav

sealed class NavScreen(val route: String) {
    data object MainScreen: NavScreen(route = "Main Screen")
    data object GetDataScreen: NavScreen(route = "Get Data Screen")
    data object GetAllUsersScreen: NavScreen(route = "Get All Users Screen")
    data object AddDataScreen: NavScreen(route = "Add Data Screen")
}

//enum class CrudScreen(@StringRes val title: Int) {
//    Start(title = R.string.main_screen),
//    Flavor(title = R.string.get_data_screen),
//    Pickup(title = R.string.get_all_users_screen),
//    Summary(title = R.string.add_data_screen)
//}