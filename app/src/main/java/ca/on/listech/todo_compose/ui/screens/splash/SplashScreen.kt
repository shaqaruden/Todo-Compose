package ca.on.listech.todo_compose.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ca.on.listech.todo_compose.R
import ca.on.listech.todo_compose.ui.theme.LOGO_SIZE
import ca.on.listech.todo_compose.ui.theme.ToDoComposeTheme
import ca.on.listech.todo_compose.ui.theme.splashScreenBackground

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.splashScreenBackground),
        contentAlignment = Alignment.Center
    ) {
        val logo = if(isSystemInDarkTheme()) R.drawable.ic_logo_dark else R.drawable.ic_logo_light

        Image(
            modifier = Modifier.size(LOGO_SIZE),
            painter = painterResource(id = logo),
            contentDescription = stringResource(R.string.app_logo)
        )
    }
}



@Composable
@Preview()
private fun SplashScreenLightPreview() {
    ToDoComposeTheme() {
        SplashScreen()
    }
}

@Composable
@Preview()
private fun SplashScreenDarkPreview() {
    ToDoComposeTheme(darkTheme = true) {
        SplashScreen()
    }
}