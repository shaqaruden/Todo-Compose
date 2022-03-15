package ca.on.listech.todo_compose.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ca.on.listech.todo_compose.data.models.Priority
import ca.on.listech.todo_compose.data.models.TodoTask
import ca.on.listech.todo_compose.ui.theme.*

@Composable
fun ListContent(
    todoTasks: List<TodoTask>,
    navigateToTaskScreen: (taskID: Int) -> Unit
) {
    if(todoTasks.isEmpty()) {
        EmptyContent()
    } else {
        LazyColumn {
            items(items = todoTasks, key = { it.id}) {
                TaskItem(todoTask = it, navigateToTaskScreen = navigateToTaskScreen)
            }
        }
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