package app.futured.brmo23.android.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import app.futured.brmo23.ConfirmationDialog

@Composable
fun ConfirmationDialog(
    dialog: ConfirmationDialog
) = AlertDialog(
    onDismissRequest = dialog.actions::onDismiss,
    confirmButton = {
        Button(onClick = dialog.actions::onPositive) {
            Text("Yes")
        }
    },
    dismissButton = {
        OutlinedButton(onClick = dialog.actions::onNegative) {
            Text("No")
        }
    },
    title = { Text(text = "Are you positive?") },
    text = { Text(text = "It's important to stay positive.") }
)
