package ca.on.listech.todo_compose.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ca.on.listech.todo_compose.R
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

    val context = LocalContext.current

    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

    Scaffold(
        topBar = {
            TaskAppBar(
                task = task,
                navigateToListScreen = {
                    if (it == Action.NO_ACTION) {
                        navigateToListScreen(it)
                    } else {
                        if(sharedViewModel.validateFields()) {
                            navigateToListScreen(it)
                        } else {
                            displayToast(context)
                        }
                    }
                }
            )
        }
    ) {
        TaskContent(
            title = title,
            onTitleChange = { sharedViewModel.updateTitle(it) },
            description = description,
            onDescriptionChange = { sharedViewModel.description.value = it },
            priority = priority,
            onPrioritySelected = { sharedViewModel.priority.value = it }
        )
    }
}

fun displayToast(context: Context) {
    Toast.makeText(context, R.string.field_validation_error, Toast.LENGTH_SHORT).show()
}