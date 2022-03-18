package ca.on.listech.todo_compose.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.on.listech.todo_compose.ui.screens.list.ListScreen
import ca.on.listech.todo_compose.ui.screens.splash.SplashScreen
import ca.on.listech.todo_compose.ui.viewmodels.SharedViewModel
import ca.on.listech.todo_compose.util.Constants
import ca.on.listech.todo_compose.util.toAction

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(route = Constants.SPLASH_SCREEN) {
        SplashScreen(navigateToListScreen)
    }
}
