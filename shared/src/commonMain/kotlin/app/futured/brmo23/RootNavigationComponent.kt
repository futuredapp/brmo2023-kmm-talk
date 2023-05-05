package app.futured.brmo23

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map

/**
 * ViewState for root navigation UI components.
 */
data class RootNavigationViewStateState(val canGoBack: Boolean)

/**
 * UI will connect to this interface in order to display navigation stack
 * for our app.
 *
 * This component is also a [SlotChild] as it can be embedded in the sheets to demonstrate
 * nested-navigation-in-sheets pattern on iOS.
 */
interface RootNavigation : SlotChild {
    val stack: Value<ChildStack<StackDestination, StackChild>>
    val viewState: Value<RootNavigationViewStateState>
    val actions: Actions

    interface Actions {
        fun androidPop()
        fun iosPop(newStack: List<Child<StackDestination, StackChild>>)
    }
}

/**
 * Underlying implementation of [RootNavigation] interface using Decompose.
 */
class RootNavigationComponent(context: ComponentContext) :
    RootNavigation,
    RootNavigation.Actions,
    ComponentContext by context {

    private val navigator = StackNavigation<StackDestination>()

    override val stack: Value<ChildStack<StackDestination, StackChild>> = childStack(
        source = navigator,
        initialStack = { listOf(StackDestination.Home) },
        handleBackButton = false,
        childFactory = ::childFactory
    )

    override val viewState: Value<RootNavigationViewStateState> = stack.map { stack ->
        RootNavigationViewStateState(canGoBack = stack.items.count() > 1)
    }

    /**
     * A factory to create all children defined in [StackDestination].
     */
    private fun childFactory(
        destination: StackDestination,
        childContext: ComponentContext,
    ): StackChild {
        return when (destination) {
            StackDestination.Home -> HomeScreenComponent(
                navigateToDetail = {
                    navigator.push(StackDestination.Detail(args = DetailScreenArgs(number = 0)))
                }
            )

            is StackDestination.Detail -> DetailScreenComponent(
                componentContext = childContext,
                navigationArgs = destination.args,
                navigateToDetail = { args ->
                    navigator.push(StackDestination.Detail(args = args))
                }
            )
        }
    }

    // region Actions impl

    override val actions: RootNavigation.Actions = this

    override fun androidPop() = navigator.pop()

    override fun iosPop(newStack: List<Child<StackDestination, StackChild>>) =
        navigator.navigate { newStack.map { it.configuration } }

    // endregion
}

