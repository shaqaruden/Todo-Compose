package ca.on.listech.todo_compose.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import ca.on.listech.todo_compose.R
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ca.on.listech.todo_compose.data.models.Priority
import ca.on.listech.todo_compose.data.models.TodoTask
import ca.on.listech.todo_compose.ui.theme.*
import ca.on.listech.todo_compose.util.Action
import ca.on.listech.todo_compose.util.RequestState
import ca.on.listech.todo_compose.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    request: RequestState<List<TodoTask>>,
    searchRequest: RequestState<List<TodoTask>>,
    lowPriorityTasks: List<TodoTask>,
    highPriorityTasks: List<TodoTask>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    navigateToTaskScreen: (taskID: Int) -> Unit,
    onSwipeToDelete: (Action, TodoTask) -> Unit
) {
 {}
    if(sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchRequest is RequestState.Success) {
                    ListItems(tasks = searchRequest.data, onSwipeToDelete, navigateToTaskScreen = navigateToTaskScreen)
                }
            }

            sortState.data == Priority.NONE -> {
                if (request is RequestState.Success) {
                    ListItems(tasks = request.data, onSwipeToDelete, navigateToTaskScreen = navigateToTaskScreen)
                }
            }

            sortState.data == Priority.LOW -> {
                ListItems(tasks = lowPriorityTasks, onSwipeToDelete, navigateToTaskScreen = navigateToTaskScreen)
            }

            sortState.data == Priority.HIGH -> {
                ListItems(tasks = highPriorityTasks, onSwipeToDelete, navigateToTaskScreen = navigateToTaskScreen)
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItems(tasks: List<TodoTask>, onSwipeToDelete: (Action, TodoTask) -> Unit, navigateToTaskScreen: (taskID: Int) -> Unit) {
    if(tasks.isEmpty()) {
        EmptyContent()
    } else {
        LazyColumn {
            items(items = tasks, key = { it.id}) {
                val dismissState = rememberDismissState()
                val dismissDirection = dismissState.dismissDirection
                val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
                if  (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                    val scope = rememberCoroutineScope()
                    scope.launch {
                        delay(300)
                        onSwipeToDelete(Action.DELETE, it)
                    }
                }
                val degrees by animateFloatAsState(targetValue = if(dismissState.targetValue == DismissValue.Default) 0f else -45f)

                var itemAppeared by remember { mutableStateOf(false) }
                LaunchedEffect(key1 = true) {
                    itemAppeared = true
                }

                AnimatedVisibility(
                    visible = itemAppeared && !isDismissed,
                    enter = expandVertically(animationSpec = tween(300)),
                    exit =  shrinkVertically(animationSpec = tween(300)),

                ) {
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = {  FractionalThreshold(0.2f)},
                        background = { RedBackground(degrees)}
                    ) {
                        TaskItem(todoTask = it, navigateToTaskScreen = navigateToTaskScreen)
                    }
                }
            }
        }
    }
}

@Composable
fun RedBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HighPriority)
            .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.rotate(degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.remove_task),
            tint = Color.White
        )
    }
}

@Composable
fun TaskItem(
    todoTask: TodoTask,
    navigateToTaskScreen: (taskID: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigateToTaskScreen(todoTask.id)
            },
        color = MaterialTheme.colors.taskitemBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
    ) {
        Column(
            Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = todoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(Modifier.padding(start = LARGE_PADDING)) {
                    Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
                        drawCircle(
                            color = todoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = todoTask.description,
                color = MaterialTheme.colors.taskItemTextColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun TaskItemPreview() {
    TaskItem(todoTask = TodoTask(0, "This is a very long title for a task", "Description", priority = Priority.MEDIUM), navigateToTaskScreen = {})
}