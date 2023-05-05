package app.futured.brmo23

interface ConfirmationSheet : SlotChild {

    val actions: Actions

    interface Actions {
        fun onPositive()
        fun onNegative()
        fun onDismiss()
    }
}

class ConfirmationSheetComponent(
    private val onPositive: () -> Unit,
    private val onNegative: () -> Unit,
    private val onDismiss: () -> Unit
) : ConfirmationSheet,
    ConfirmationSheet.Actions {

    override val actions: ConfirmationSheet.Actions = this

    override fun onPositive() = onPositive.invoke()

    override fun onNegative() = onNegative.invoke()

    override fun onDismiss() = onDismiss.invoke()
}
