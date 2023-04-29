@file:OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@file:Suppress("AnimatedContentLabel")

package app.futured.brmo23.android.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.futured.brmo23.navigation_pure.SharedDestination
import app.futured.brmo23.navigation_pure.SharedNavigation
import app.futured.brmo23.navigation_pure.SharedNavigationImpl
import app.futured.brmo23.navigation_pure.SharedNavigationStack

@Composable
fun SharednavigationStackDemo(
    modifier: Modifier = Modifier,
    sharedNavigation: SharedNavigation
) {
val stack by sharedNavigation.stack.collectAsState()

NavigationHost(
    stack = stack
) { topOfTheStack ->
    when (topOfTheStack) {
        SharedDestination.Home -> HomeView(
            onButtonClick = { sharedNavigation.navigator.goToDetail() }
        )
        else -> Unit
    }
}

BackHandler { sharedNavigation.navigator.pop() }
}

@Composable
private fun NavigationHost(
    stack: SharedNavigationStack,
    displayView: @Composable (dest: SharedDestination) -> Unit
): Unit = TODO()

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
