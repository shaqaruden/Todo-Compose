package ca.on.listech.todo_compose.ui.screens.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ca.on.listech.todo_compose.R
import ca.on.listech.todo_compose.data.models.Priority
import ca.on.listech.todo_compose.data.models.TodoTask
import ca.on.listech.todo_compose.ui.theme.topAppBarBackgroundColor
import ca.on.listech.todo_compose.ui.theme.topAppBarContentColor
import ca.on.listech.todo_compose.util.Action

@Composable
fun TaskAppBar(task: TodoTask?, navigateToListScreen: (Action) -> Unit) {
    when(task) {
        null -> NewTaskAppBar(navigateToListScreen)
        else -> ExistingTaskAppBar(task, navigateToListScreen)
    }
}

@Composable
fun NewTaskAppBar(navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = {
            BackAction(navigateToListScreen)
        },
        title = {
            Text(
                text = stringResource(R.string.new_task_title),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(navigateToListScreen)
        }
    )
}

@Composable
fun BackAction(onBackClicked: (Action) -> Unit) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_to_tasks),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(onAddClicked: (Action) -> Unit) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.add_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBar(task: TodoTask, navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = { CloseAction(navigateToListScreen) },
        title = {
            Text(
                text = task.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            DeleteAction(navigateToListScreen)
            UpdateAction(navigateToListScreen)
        }
    )
}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(R.string.cancel_changes),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(onDeleteClicked: (Action) -> Unit) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.add_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(onUpdateClicked: (Action) -> Unit) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.update_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}


@Composable
@Preview(showBackground = true)
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Composable
@Preview(showBackground = true)
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(task = TodoTask(
        0,
        "Sample Task",
        "Sample Task Description",
        Priority.LOW
    ), navigateToListScreen = {})
}