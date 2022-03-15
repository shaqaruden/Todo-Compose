package ca.on.listech.todo_compose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.on.listech.todo_compose.ui.screens.task.TaskScreen
import ca.on.listech.todo_compose.util.Action
import ca.on.listech.todo_compose.util.Constants.LIST_ARGUMENT_KEY
import ca.on.listech.todo_compose.util.Constants.LIST_SCREEN
import ca.on.listech.todo_compose.util.Constants.TASK_ARGUMENT_KEY
import ca.on.listech.todo_compose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {
        val taskID = it.arguments!!.getInt(TASK_ARGUMENT_KEY)

        TaskScreen(navigateToListScreen = navigateToListScreen)
    }
}

