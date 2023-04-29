@file:OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@file:Suppress("AnimatedContentLabel")

package app.futured.brmo23.android.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PureComposeNavigationStackDemo(
    modifier: Modifier = Modifier
) {
    val stack = remember { mutableStateListOf<Destination>(Destination.Login) }

    NavigationHost(
        stack = stack
    ) { topOfTheStack ->
        when (topOfTheStack) {
            Destination.Home -> HomeView(
                onButtonClick = { stack.add(Destination.Detail) }
            )

            else -> Unit
        }
    }

    BackHandler { stack.removeLast() }
}

@Composable
private fun NavigationHost(
    stack: List<Destination>,
    displayView: @Composable (dest: Destination) -> Unit
): Unit = TODO()

private sealed class Destination {
    object Login : Destination()
    object Home : Destination()
    object Detail : Destination()
}

private fun getAppbarTitle(destination: Destination) = when (destination) {
    Destination.Login -> "Login"
    Destination.Home -> "Home"
    is Destination.Detail -> "Detail"
}

@Composable
private fun AppBar(
    topOfTheStack: Destination,
    backButtonVisible: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = getAppbarTitle(topOfTheStack)) },
        navigationIcon = {
            if (backButtonVisible) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Go back")
                }
            }
        }
    )
}

@Composable
private fun LoginView(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onButtonClick) {
                Text("Log In")
            }
        }
    }
}

@Composable
private fun HomeView(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Home", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = onButtonClick) {
                Text("Open detail")
            }
        }
    }
}

@Composable
private fun DetailView(modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hello, Brno!", style = MaterialTheme.typography.headlineSmall)
        }
    }
}
