package app.futured.brmo23

interface ConfirmationDialog : SlotChild {

    val actions: Actions

    interface Actions {
        fun onPositive()
        fun onNegative()
        fun onDismiss()
    }
}

class ConfirmationDialogComponent(
    private val onPositive: () -> Unit,
    private val onNegative: () -> Unit,
    private val onDismiss: () -> Unit
) : ConfirmationDialog,
    ConfirmationDialog.Actions {

    override val actions: ConfirmationDialog.Actions = this

    override fun onPositive() = onPositive.invoke()

    override fun onNegative() = onNegative.invoke()

    override fun onDismiss() = onDismiss.invoke()
}
