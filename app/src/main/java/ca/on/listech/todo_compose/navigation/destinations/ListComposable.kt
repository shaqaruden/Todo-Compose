package ca.on.listech.todo_compose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import ca.on.listech.todo_compose.ui.screens.list.ListScreen
import ca.on.listech.todo_compose.ui.viewmodels.SharedViewModel
import ca.on.listech.todo_compose.util.Action
import ca.on.listech.todo_compose.util.Constants.LIST_ARGUMENT_KEY
import ca.on.listech.todo_compose.util.Constants.LIST_SCREEN
import ca.on.listech.todo_compose.util.toAction

@ExperimentalAnimationApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskID: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        val action = it.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        var myAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) }

        LaunchedEffect(key1 = myAction) {
            if (action != myAction) {
                myAction = action
                sharedViewModel.action.value = action
            }
        }

        val databaseAction by sharedViewModel.action

        ListScreen(
            action = databaseAction,
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}

