package app.futured.brmo23.navigation_decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

sealed class StackDestination : Parcelable {
    @Parcelize
    object Home : StackDestination()
    @Parcelize
    data class Detail(val argument: String) : StackDestination()
}

class RootNavigationComponent(context: ComponentContext) : ComponentContext by context {
    private val navigator = StackNavigation<StackDestination>()
    val stack = childStack(
        source = navigator,
        initialStack = { listOf(StackDestination.Home) },
        childFactory = { destination, childContext ->
            when (destination) {
                StackDestination.Home -> createHomeScreenComponent(childContext)
                is StackDestination.Detail -> createDetailScreenComponent(childContext, destination.argument)
            }
        }
    )
}

private fun createHomeScreenComponent(context: ComponentContext): HomeScreenComponent = TODO()
private fun createDetailScreenComponent(
    context: ComponentContext,
    argument: String
): DetailScreenComponent = TODO()

