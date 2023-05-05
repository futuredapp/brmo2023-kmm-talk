package app.futured.brmo23

import com.arkivanov.decompose.ComponentContext

/**
 * Home Screen interface that will be presented to UI.
 */
interface HomeScreen : StackChild {

    val actions: Actions

    /**
     * Interface defining actions callable from UI.
     */
    interface Actions {
        fun openDetailClicked() = Unit
    }
}

/**
 * Underlying implementation of [HomeScreen] interface using Decompose.
 *
 * We actually do not need to pass [ComponentContext] in here, because this component does not use
 * any Decompose features like Lifecycle, InstanceKeeper, Navigation APIs, etc.
 */
internal class HomeScreenComponent(
    private val navigateToDetail: () -> Unit
) : HomeScreen,
    HomeScreen.Actions {

    override val actions: HomeScreen.Actions = this

    override fun openDetailClicked() = navigateToDetail.invoke()
}
