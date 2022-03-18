package ca.on.listech.todo_compose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import ca.on.listech.todo_compose.ui.screens.task.TaskScreen
import ca.on.listech.todo_compose.ui.viewmodels.SharedViewModel
import ca.on.listech.todo_compose.util.Action
import ca.on.listech.todo_compose.util.Constants.TASK_ARGUMENT_KEY
import ca.on.listech.todo_compose.util.Constants.TASK_SCREEN

@ExperimentalAnimationApi
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
        enterTransition = {
            slideInHorizontally(tween(300), initialOffsetX = { -it })
        }
    ) {
        val taskID = it.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskID) {
            sharedViewModel.getTask(taskID = taskID)
        }
        val task by sharedViewModel.task.collectAsState()

        LaunchedEffect(key1 = task) {
            if(task != null || taskID == -1) {
                sharedViewModel.selectTask(task = task)
            }
        }

        TaskScreen(
            task = task,
            navigateToListScreen = navigateToListScreen,
            sharedViewModel = sharedViewModel
        )
    }
}

