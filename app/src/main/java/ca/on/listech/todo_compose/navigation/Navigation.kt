package ca.on.listech.todo_compose.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import ca.on.listech.todo_compose.navigation.destinations.listComposable
import ca.on.listech.todo_compose.navigation.destinations.splashComposable
import ca.on.listech.todo_compose.navigation.destinations.taskComposable
import ca.on.listech.todo_compose.ui.viewmodels.SharedViewModel
import ca.on.listech.todo_compose.util.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@Composable
fun SetupNavigation (navController: NavHostController, sharedViewModel: SharedViewModel) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    AnimatedNavHost(navController = navController, startDestination = SPLASH_SCREEN) {
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

