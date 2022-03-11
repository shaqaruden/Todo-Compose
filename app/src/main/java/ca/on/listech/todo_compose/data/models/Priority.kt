package ca.on.listech.todo_compose.data.models

import androidx.compose.ui.graphics.Color
import ca.on.listech.todo_compose.ui.theme.HighPriority
import ca.on.listech.todo_compose.ui.theme.LowPriority
import ca.on.listech.todo_compose.ui.theme.MediumPriority
import ca.on.listech.todo_compose.ui.theme.NonePriority

enum class Priority(val color: Color) {
    HIGH(HighPriority),
    MEDIUM(MediumPriority),
    LOW(LowPriority),
    NONE(NonePriority)
}