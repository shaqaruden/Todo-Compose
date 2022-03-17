package ca.on.listech.todo_compose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import ca.on.listech.todo_compose.data.models.Priority
import ca.on.listech.todo_compose.data.models.TodoTask
import ca.on.listech.todo_compose.ui.viewmodels.SharedViewModel
import ca.on.listech.todo_compose.util.Action

@Composable
fun TaskScreen(
    task: TodoTask?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBar(task = task, navigateToListScreen = navigateToListScreen)
        }
    ) {
        TaskContent(
            title = title,
            onTitleChange = { sharedViewModel.title.value = it },
            description = description,
            onDescriptionChange = { sharedViewModel.description.value = it },
            priority = priority,
            onPrioritySelected = { sharedViewModel.priority.value = it }
        )
    }
}