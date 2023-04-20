package app.futured.brmo23

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

/**
 * This interface exposes public properties of Root Navigation component used by UI layer.
 */
interface RootNavigation {
    val stack: Value<ChildStack<Destination, *>>
    val actions: Actions

    interface Actions {
        /**
         * This method is invoked by SwiftUI NavigationStack's path binding whenever
         * user performs back navigation -- either by gestures, using back button menu,
         * or whatever else type of navigation the View provides.
         *
         * This method allows SwiftUI NavigationStack to pop multiple destinations off the backstack
         * by providing a [iosPath] that contains new desired backstack state (that's how SwiftUI binding works).
         *
         * See <a>https://developer.apple.com/documentation/swiftui/navigationstack</a> to lear more
         * about NavigationStack mechanics.
         */
        fun iosPopTo(iosPath: List<Child<Destination, *>>) = Unit

        /**
         * This method is called by Android when user performs back navigation.
         * Unlike on iOS, on Android only single destination can popped off the stack.
         */
        fun androidPop() = Unit
    }
}

/**
 * The implementation of [RootNavigation] component with all the logic necessary for managing
 * child stack.
 */
class RootNavigationComponent(
    componentContext: ComponentContext
) : RootNavigation,
    RootNavigation.Actions,
    ComponentContext by componentContext {

    private val navigator = StackNavigation<Destination>()

    override val stack: Value<ChildStack<Destination, *>> = childStack(
        source = navigator,
        initialStack = { listOf(Destination.Home) },
        handleBackButton = true,
        childFactory = { destination, childContext ->
            when (destination) {
                Destination.Home -> HomeScreenComponent(
                    componentContext = childContext,
                    onOpenDetail = { argument ->
                        navigator.push(Destination.Detail(argument))
                    }
                )

                is Destination.Detail -> DetailScreenComponent(
                    componentContext = childContext,
                    argument = destination.argument,
                    onOpenDetail = { argument ->
                        navigator.push(Destination.Detail(argument))
                    }
                )
            }
        }
    )

    override val actions: RootNavigation.Actions = this

    // region UI Actions implementation

    override fun androidPop() = navigator.pop()

    override fun iosPopTo(iosPath: List<Child<Destination, *>>) =
        navigator.navigate { currentStack ->
            listOf(currentStack.first()) + iosPath.map { it.configuration }
        }

    // endregion
}

/**
 * A mock implementation of [RootNavigation] which is useful for displaying
 * UI previews.
 */
@Suppress("FunctionName")
fun RootNavigationMock(): RootNavigation = object : RootNavigation {
    override val stack: Value<ChildStack<Destination, *>> = MutableValue(
        ChildStack(
            configuration = Destination.Home,
            instance = HomeScreenMock()
        )
    )
    override val actions: RootNavigation.Actions = object : RootNavigation.Actions {}
}
