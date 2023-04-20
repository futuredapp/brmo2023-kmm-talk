package app.futured.brmo23

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

sealed class Destination : Parcelable {

    @Parcelize
    object Home : Destination()

    @Parcelize
    data class Detail(val argument: String) : Destination()
}

sealed class SlotDestination : Parcelable {

    @Parcelize
    object Sheet : SlotDestination()
}
