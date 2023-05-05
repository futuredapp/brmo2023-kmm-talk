package app.futured.brmo23

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

/**
 * [DetailScreen] state to be rendered by UI.
 */
data class DetailViewState(
    val text: String = "",
    val dialogResult: String = "none",
    val sheetResult: String = "none"
)

/**
 * Detail Screen interface that will be presented to UI.
 */
interface DetailScreen : StackChild {

    val state: Value<DetailViewState>
    val actions: Actions
    val slot: Value<ChildSlot<SlotDestination, SlotChild>>

    /**
     * Interface defining actions callable from UI.
     */
    interface Actions {
        fun openAnotherDetailClicked() = Unit

        fun openConfirmationDialogClicked() = Unit
        fun openConfirmationSheetClicked() = Unit
        fun openSheetWithNavigationClicked() = Unit
        fun sheetWithNavigationDismissed() = Unit
    }
}

/**
 * Input arguments to [DetailScreen] navigation destination.
 */
@Parcelize
data class DetailScreenArgs(val number: Int) : Parcelable

/**
 * Underlying implementation of [DetailScreen] interface using Decompose.
 */
internal class DetailScreenComponent(
    componentContext: ComponentContext,
    private val navigationArgs: DetailScreenArgs,
    private val navigateToDetail: (navigationArgs: DetailScreenArgs) -> Unit
) : DetailScreen,
    DetailScreen.Actions,
    ComponentContext by componentContext {

    private val mutableState: MutableValue<DetailViewState> =
        MutableValue(DetailViewState(text = "The number is ${navigationArgs.number}"))

    /*
    We need dem explicit backing fields ASAP
    https://github.com/Kotlin/KEEP/blob/explicit-backing-fields-re/proposals/explicit-backing-fields.md
     */
    override val state: Value<DetailViewState> = mutableState
    override val actions: DetailScreen.Actions = this

    private val slotNavigator = SlotNavigation<SlotDestination>()
    override val slot: Value<ChildSlot<SlotDestination, SlotChild>> = childSlot(
        source = slotNavigator,
        initialConfiguration = { null },
        handleBackButton = false,
        childFactory = ::slotChildFactory
    )

    private fun slotChildFactory(destination: SlotDestination, childContext: ComponentContext): SlotChild {
        return when (destination) {
            SlotDestination.ConfirmationDialog -> ConfirmationDialogComponent(
                onPositive = {
                    mutableState.update { state -> state.copy(dialogResult = "positive") }
                    slotNavigator.dismiss()
                },
                onNegative = {
                    mutableState.update { state -> state.copy(dialogResult = "negative") }
                    slotNavigator.dismiss()
                },
                onDismiss = {
                    slotNavigator.dismiss()
                }
            )

            SlotDestination.ConfirmationSheet -> ConfirmationSheetComponent(
                onPositive = {
                    mutableState.update { state -> state.copy(sheetResult = "positive") }
                    slotNavigator.dismiss()
                },
                onNegative = {
                    mutableState.update { state -> state.copy(sheetResult = "negative") }
                    slotNavigator.dismiss()
                },
                onDismiss = {
                    slotNavigator.dismiss()
                }
            )

            SlotDestination.SheetWithNavigation -> RootNavigationComponent(childContext)
        }
    }

    // region Actions interface implementation

    override fun openAnotherDetailClicked() = navigateToDetail.invoke(
        DetailScreenArgs(number = navigationArgs.number + 1)
    )

    override fun openConfirmationDialogClicked() = slotNavigator.activate(SlotDestination.ConfirmationDialog)

    override fun openConfirmationSheetClicked() = slotNavigator.activate(SlotDestination.ConfirmationSheet)

    override fun openSheetWithNavigationClicked() = slotNavigator.activate(SlotDestination.SheetWithNavigation)

    override fun sheetWithNavigationDismissed() = slotNavigator.dismiss()

    // endregion
}
