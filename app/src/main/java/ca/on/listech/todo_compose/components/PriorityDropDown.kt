package ca.on.listech.todo_compose.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ca.on.listech.todo_compose.R
import ca.on.listech.todo_compose.data.models.Priority
import ca.on.listech.todo_compose.ui.theme.*

@Composable
fun PriorityDropDown(
    modifier: Modifier = Modifier,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    var parentSize by remember { mutableStateOf(IntSize.Zero) }

    Row(
        modifier = modifier
            .padding(top = MEDIUM_PADDING)
            .fillMaxWidth()
            .onGloballyPositioned { parentSize = it.size }
            .height(PRIORITY_DROPDOWN_HEIGHT)
            .clip(MaterialTheme.shapes.small)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(modifier = Modifier
            .padding(LARGE_PADDING)
            .size(PRIORITY_INDICATOR_SIZE)) {
            drawCircle(color = priority.color)
        }
        Text(modifier = Modifier.weight(1f), text = priority.name, style = MaterialTheme.typography.subtitle2)
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(angle),
            onClick = { expanded = true }
        ) {
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = stringResource(id = R.string.priority_dropdown))
        }
        DropdownMenu(modifier = Modifier.width(with(LocalDensity.current) { parentSize.width.toDp() }), expanded = expanded, onDismissRequest = { expanded = false }) {

            Priority.values().slice(0..2).forEach {
                DropdownMenuItem(onClick = {
                    expanded = false
                    onPrioritySelected(it)
                }) {
                    PriorityItem(priority = it)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PriorityDropdownPreview() {
    PriorityDropDown(priority = Priority.LOW, onPrioritySelected = {})
}