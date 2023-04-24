//package app.futured.brmo23
//
//import com.arkivanov.decompose.ComponentContext
//
//interface HomeScreen {
//
//    val actions: Actions
//
//    interface Actions {
//        fun openDetail(argument: String) = Unit
//    }
//}
//
//class HomeScreenComponent(
//    componentContext: ComponentContext,
//    private val onOpenDetail: (argument: String) -> Unit
//) : HomeScreen,
//    HomeScreen.Actions,
//    ComponentContext by componentContext {
//
//    override val actions: HomeScreen.Actions = this
//    override fun openDetail(argument: String) = onOpenDetail(argument)
//}
//
//@Suppress("FunctionName")
//fun HomeScreenMock(): HomeScreen = object : HomeScreen {
//    override val actions: HomeScreen.Actions = object : HomeScreen.Actions {}
//}
