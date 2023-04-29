package app.futured.brmo23.navigation_pure

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class SharedDestination {
    object Login : SharedDestination()
    object Home : SharedDestination()
    data class Detail(val id: String) : SharedDestination()
}

data class SharedNavigationStack(val children: List<SharedDestination>)

interface SharedNavigator {
    fun goToHomeScreen()
    fun goToDetail()
    fun pop() // Android pop
    fun popTo(newStack: List<SharedDestination>) // iOS Pop
}

interface SharedNavigation {
    val stack: StateFlow<SharedNavigationStack>
    val navigator: SharedNavigator
}

class SharedNavigationImpl : SharedNavigation {

    private val _stack: MutableStateFlow<SharedNavigationStack> = MutableStateFlow(
        SharedNavigationStack(children = listOf(SharedDestination.Login))
    )

    override val stack: StateFlow<SharedNavigationStack> = _stack

    override val navigator: SharedNavigator = object : SharedNavigator {

        override fun goToHomeScreen() {
            _stack.value = _stack.value.copy(children = _stack.value.children + SharedDestination.Home)
        }

        override fun goToDetail() {
            _stack.value =
                _stack.value.copy(children = _stack.value.children + SharedDestination.Detail("Hello from Kotlin!"))
        }

        override fun pop() {
            _stack.value = _stack.value.copy(children = _stack.value.children.dropLast(1))
        }

        override fun popTo(newStack: List<SharedDestination>) {
            _stack.value = _stack.value.copy(children = newStack)
        }
    }
}
