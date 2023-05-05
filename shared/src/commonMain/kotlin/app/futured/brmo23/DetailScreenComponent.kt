package app.futured.brmo23

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

/**
 * [DetailScreen] state to be rendered by UI.
 */
data class DetailViewState(val text: String = "")

/**
 * Detail Screen interface that will be presented to UI.
 */
interface DetailScreen {

    val state: Value<DetailViewState>
    val actions: Actions

    /**
     * Interface defining actions callable from UI.
     */
    interface Actions {
        fun openAnotherDetailClicked() = Unit
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
    StackComponent,
    ComponentContext by componentContext {

    private val mutableState: MutableValue<DetailViewState> =
        MutableValue(DetailViewState(text = "The number is ${navigationArgs.number}"))

    /*
    We need dem explicit backing fields ASAP
    https://github.com/Kotlin/KEEP/blob/explicit-backing-fields-re/proposals/explicit-backing-fields.md
     */
    override val state: Value<DetailViewState> = mutableState
    override val actions: DetailScreen.Actions = this

    // region Actions interface implementation

    override fun openAnotherDetailClicked() = navigateToDetail.invoke(
        DetailScreenArgs(number = navigationArgs.number + 1)
    )

    // endregion
}
