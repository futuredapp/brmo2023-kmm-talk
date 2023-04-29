package app.futured.brmo23.navigation_decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

interface DetailScreen {

    val state: Value<DetailState>
    val slot: Value<ChildSlot<SlotDestination, *>>
    fun openAnotherDetail() = Unit
    fun openSheet() = Unit
    fun dismissSheet() = Unit
}

data class DetailState(val text: String = "")

class DetailScreenComponent(
    componentContext: ComponentContext,
    private val argument: String,
    private val onOpenDetail: (argument: String) -> Unit,
) : DetailScreen, ComponentContext by componentContext {

    private val stateInternal = MutableValue(DetailState(text = argument))
    override val state: Value<DetailState> = stateInternal

    private val slotNavigator = SlotNavigation<SlotDestination>()

    override val slot: Value<ChildSlot<SlotDestination, *>> = childSlot(
        source = slotNavigator,
        initialConfiguration = { null },
        handleBackButton = true,
        childFactory = { destination, childContext ->
            when (destination) {
                SlotDestination.ConfirmationDialog -> TODO()
                SlotDestination.ConfirmationSheet -> TODO()
                SlotDestination.SheetWithNavigation -> TODO()
            }
        }
    )

    override fun openAnotherDetail() = onOpenDetail("Detail on top of $argument")
    override fun openSheet() = slotNavigator.activate(SlotDestination.ConfirmationSheet)
    override fun dismissSheet() = slotNavigator.dismiss()
}

@Suppress("FunctionName")
fun DetailScreenMock(): DetailScreen = object : DetailScreen {
    override val state: Value<DetailState> =
        MutableValue(DetailState(text = "Preview of Detail Screen"))
    override val slot: Value<ChildSlot<SlotDestination, *>> =
        MutableValue(ChildSlot<SlotDestination, Any>())
}
