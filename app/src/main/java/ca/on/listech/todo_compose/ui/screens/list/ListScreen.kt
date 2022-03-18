package ca.on.listech.todo_compose.ui.screens.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ca.on.listech.todo_compose.R
import ca.on.listech.todo_compose.ui.theme.fabBackgroundColor
import ca.on.listech.todo_compose.ui.viewmodels.SharedViewModel
import ca.on.listech.todo_compose.util.Action
import ca.on.listech.todo_compose.util.SearchAppBarState
import kotlinx.coroutines.launch

@Composable
fun ListScreen(
    action: Action,
    navigateToTaskScreen: (taskID: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
        sharedViewModel.readSortState()
    }

    LaunchedEffect(key1 = action) {
        sharedViewModel.handleDatabaseActions(action)
    }



    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val sortByLowPriority by sharedViewModel.sortedByLowPriority.collectAsState()
    val sortByHighPriority by sharedViewModel.sortedByHighPriority.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState
    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        onComplete = { sharedViewModel.action.value = it },
        onRestoreClicked = {
          sharedViewModel.action.value = it
        },
        taskTitle = sharedViewModel.title.value,
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 ListAppBar(
                     sharedViewModel = sharedViewModel,
                     searchAppBarState = searchAppBarState,
                     searchTextState = searchTextState
                 )
        },
        floatingActionButton = {
            ListFAB(onFABClicked = navigateToTaskScreen)
        }
    ) {
        ListContent(
            request = allTasks,
            searchRequest = searchedTasks,
            lowPriorityTasks = sortByLowPriority,
            highPriorityTasks = sortByHighPriority,
            sortState = sortState,
            searchAppBarState = searchAppBarState,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = { action, task ->
                sharedViewModel.selectTask(task)
                sharedViewModel.action.value = action
            }
        )
    }
}

@Composable
fun ListFAB(onFABClicked: (taskID: Int) -> Unit) {
    FloatingActionButton(
        onClick = { onFABClicked(-1) },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_task),
            tint = Color.White
        )

    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    onComplete: (Action) -> Unit,
    onRestoreClicked: (action: Action) -> Unit,
    taskTitle: String,
    action: Action
) {


    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = snackbarMessage(action, taskTitle),
                    actionLabel = setActionLabel(action)
                )
                restoreDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onRestoreClicked = onRestoreClicked
                )
            }
            onComplete(Action.NO_ACTION)
        }
    }
}

private fun snackbarMessage(action: Action, taskTitle: String): String {
    return when(action) {
        Action.DELETE_ALL -> "All Tasks Deleted"
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if(action.name == "DELETE") {
        "UNDO"
    } else {
        "Ok"
    }
}

private fun restoreDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onRestoreClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onRestoreClicked(Action.UNDO)
    }
}