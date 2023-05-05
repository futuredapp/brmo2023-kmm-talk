package app.futured.brmo23.android.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import app.futured.brmo23.DetailScreen
import app.futured.brmo23.HomeScreen
import app.futured.brmo23.RootNavigation
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@Composable
fun RootNavigationUi(
    navigation: RootNavigation,
    modifier: Modifier = Modifier
) {
    val viewState by navigation.viewState.subscribeAsState()
    val actions = navigation.actions

    Children(
        modifier = modifier,
        stack = navigation.stack,
        animation = stackAnimation(slide(animationSpec = tween(durationMillis = 200)))
    ) { child ->
        when (val instance = child.instance) {
            is HomeScreen -> HomeScreenUi(screen = instance, modifier = Modifier.fillMaxSize())
            is DetailScreen -> DetailScreenUi(screen = instance, modifier = Modifier.fillMaxSize())
            is RootNavigation -> error("Embedding RootNavigation into")
        }
    }

    BackHandler(enabled = viewState.canGoBack) {
        actions.androidPop()
    }
}
