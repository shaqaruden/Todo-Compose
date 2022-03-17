package ca.on.listech.todo_compose.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.on.listech.todo_compose.data.models.Priority
import ca.on.listech.todo_compose.ui.theme.LARGE_PADDING
import ca.on.listech.todo_compose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityItem(priority: Priority) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
            drawCircle(color =  priority.color)
        }
        Text(
            modifier = Modifier.padding(start = LARGE_PADDING),
            text = priority.name,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PriorityItemPreview() {
    PriorityItem(priority = Priority.HIGH)
}