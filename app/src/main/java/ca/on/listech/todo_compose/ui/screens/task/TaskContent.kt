package ca.on.listech.todo_compose.ui.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ca.on.listech.todo_compose.R
import ca.on.listech.todo_compose.components.PriorityDropDown
import ca.on.listech.todo_compose.data.models.Priority
import ca.on.listech.todo_compose.ui.theme.LARGE_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
     Column(
         Modifier
             .fillMaxSize()
             .padding(LARGE_PADDING)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = onTitleChange,
            label = { Text(stringResource(R.string.title_textfield_label)) },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )
         PriorityDropDown(
             priority = priority,
             onPrioritySelected = onPrioritySelected
         )
         OutlinedTextField(
             modifier = Modifier.fillMaxSize(),
             value = description,
             onValueChange = onDescriptionChange,
             label = { Text(stringResource(R.string.description_textfield_label)) },
             textStyle = MaterialTheme.typography.body1
         )
     }
}

@Composable
@Preview(showBackground = true)
private fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}