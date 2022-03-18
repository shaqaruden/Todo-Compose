package ca.on.listech.todo_compose.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ca.on.listech.todo_compose.navigation.destinations.listComposable
import ca.on.listech.todo_compose.navigation.destinations.splashComposable
import ca.on.listech.todo_compose.navigation.destinations.taskComposable
import ca.on.listech.todo_compose.ui.viewmodels.SharedViewModel
import ca.on.listech.todo_compose.util.Constants.LIST_SCREEN
import ca.on.listech.todo_compose.util.Constants.SPLASH_SCREEN

@Composable
fun SetupNavigation (navController: NavHostController, sharedViewModel: SharedViewModel) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        splashComposable {
            Log.d("Navigation", "Navigating to listScreen")
            screen.splash()
        }
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable(sharedViewModel = sharedViewModel, navigateToListScreen = screen.task)
    }

}

