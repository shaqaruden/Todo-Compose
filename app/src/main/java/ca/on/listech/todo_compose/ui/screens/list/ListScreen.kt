package ca.on.listech.todo_compose.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ca.on.listech.todo_compose.R

@Composable
fun ListScreen(navigateToTaskScreen: (Int) -> Unit) {
    Scaffold(
        topBar = {
                 ListAppBar()
        },
        floatingActionButton = {
            ListFAB(onFABClicked = navigateToTaskScreen)
        }
    ) {
        
    }
}

@Composable
fun ListFAB(onFABClicked: (Int) -> Unit) {
    FloatingActionButton(onClick = { onFABClicked(-1)}) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_task),
            tint = Color.White
        )

    }
}

@Composable
@Preview(showBackground = true)
fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}