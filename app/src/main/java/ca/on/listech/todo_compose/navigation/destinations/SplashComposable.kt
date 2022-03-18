package ca.on.listech.todo_compose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ca.on.listech.todo_compose.ui.screens.splash.SplashScreen
import ca.on.listech.todo_compose.util.Constants

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = Constants.SPLASH_SCREEN,
        exitTransition = {
            when (targetState.destination.route) {
                "list/{action}" ->
                    slideOutVertically(tween(1000), targetOffsetY = { -it })
                    else -> null
            }
        }
    ) {
        SplashScreen(navigateToListScreen)
    }
}
