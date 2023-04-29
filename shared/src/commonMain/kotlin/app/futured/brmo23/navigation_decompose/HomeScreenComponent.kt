package app.futured.brmo23.navigation_decompose//package app.futured.brmo23

import com.arkivanov.decompose.ComponentContext

interface HomeScreen {
    fun openDetail(argument: String) = Unit
}

class HomeScreenComponent(
    componentContext: ComponentContext,
    private val onOpenDetail: (argument: String) -> Unit
) : HomeScreen, ComponentContext by componentContext {

    override fun openDetail(argument: String) = onOpenDetail(argument)
}

@Suppress("FunctionName")
fun HomeScreenMock(): HomeScreen = object : HomeScreen {}
