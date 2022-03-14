package ca.on.listech.todo_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ca.on.listech.todo_compose.navigation.destinations.ListComposable
import ca.on.listech.todo_compose.navigation.destinations.TaskComposable
import ca.on.listech.todo_compose.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation (navController: NavHostController) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        ListComposable(navigateToScreen = screen.task)
        TaskComposable(navigateToListScreen = screen.list)
    }

}

