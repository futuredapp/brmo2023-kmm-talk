package app.futured.brmo23.android.navigation

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.futured.brmo23.navigation_decompose.DetailScreenComponent
import app.futured.brmo23.navigation_decompose.HomeScreenComponent
import app.futured.brmo23.navigation_decompose.RootNavigationComponent
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation

@Composable
fun BrMo23App(
    component: RootNavigationComponent,
    modifier: Modifier = Modifier
) {
    Children(
        modifier = modifier,
        stack = component.stack,
        animation = stackAnimation(slide(animationSpec = tween(durationMillis = 200)))
    ) { child ->
        when (val childComponent = child.instance) {
            is HomeScreenComponent -> HomeScreenUi(component = childComponent)
            is DetailScreenComponent -> DetailScreenUi(component = childComponent)
        }
    }
}

@Composable
private fun HomeScreenUi(component: HomeScreenComponent) = Unit

@Composable
private fun DetailScreenUi(component: DetailScreenComponent) = Unit
