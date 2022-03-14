package ca.on.listech.todo_compose.navigation

import androidx.navigation.NavHostController
import ca.on.listech.todo_compose.util.Action
import ca.on.listech.todo_compose.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = {
        navController.navigate("list/${it.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
    val task: (Int) -> Unit = {
        navController.navigate("task/$it")
    }
}