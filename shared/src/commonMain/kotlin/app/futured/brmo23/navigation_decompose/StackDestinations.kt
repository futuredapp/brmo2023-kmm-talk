package app.futured.brmo23.navigation_decompose

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

/**
 * Destinations that can be used with stack navigation.
 * This is a Decompose configuration class for use with ChildStack APIs and needs to be Parcelable
 * in order for Decompose to retain the navigation stack across configuration changes
 * on Android.
 *
 * Decompose provides Parcelable implementation in common source set by delegating
 * to the native Android implementation in android target of library.
 */
sealed class StackDestination : Parcelable {

    @Parcelize
    object Home : StackDestination()

    @Parcelize
    data class Detail(val args: DetailScreenArgs) : StackDestination()
}

/**
 * Interface that each component placed on a stack must implement.
 */
interface StackComponent
