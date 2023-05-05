package app.futured.brmo23

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

/**
 * Destinations that can be used with Slot Navigation.
 */
sealed class SlotDestination : Parcelable {

    @Parcelize
    object ConfirmationDialog : SlotDestination()

    @Parcelize
    object ConfirmationSheet : SlotDestination()
}

/**
 * Interface that each component placed into a Slot must implement
 */
sealed interface SlotChild
