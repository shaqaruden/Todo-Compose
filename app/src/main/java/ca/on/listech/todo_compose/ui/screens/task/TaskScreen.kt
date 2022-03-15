package ca.on.listech.todo_compose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import ca.on.listech.todo_compose.util.Action

@Composable
fun TaskScreen(navigateToListScreen: (Action) -> Unit) {
    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen)
        }
    ) {

    }
}