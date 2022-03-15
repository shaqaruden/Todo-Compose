package ca.on.listech.todo_compose.ui.screens.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ca.on.listech.todo_compose.R
import ca.on.listech.todo_compose.ui.theme.topAppBarBackgroundColor
import ca.on.listech.todo_compose.ui.theme.topAppBarContentColor
import ca.on.listech.todo_compose.util.Action

@Composable
fun TaskAppBar(navigateToListScreen: (Action) -> Unit) {
    NewTaskAppBar(navigateToListScreen)
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
@Preview(showBackground = true)
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}