@file:OptIn(ExperimentalMaterial3Api::class)

package app.futured.brmo23.android.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.futured.brmo23.ConfirmationSheet
import app.futured.brmo23.android.ui_tooling.Spacer
import kotlinx.coroutines.CompletionHandler
import kotlinx.coroutines.launch

@Composable
fun ConfirmationSheet(sheet: ConfirmationSheet) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    fun hideWithAction(completionHandler: CompletionHandler) {
        coroutineScope
            .launch { sheetState.hide() }
            .invokeOnCompletion(completionHandler)
    }

    ModalBottomSheet(
        modifier = Modifier.heightIn(min = 1.dp),
        onDismissRequest = sheet.actions::onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Are you positive?", style = MaterialTheme.typography.titleMedium)
            Spacer(8.dp)
            Text("It's important to stay positive.")
            Spacer(16.dp)
            Row {
                OutlinedButton(onClick = { hideWithAction { sheet.actions.onNegative() } }) {
                    Text("Nope")
                }
                Spacer(32.dp)
                Button(onClick = { hideWithAction { sheet.actions.onPositive() } }) {
                    Text("Yup")
                }
            }
        }
    }

    BackHandler { hideWithAction { sheet.actions.onDismiss() } }
}
