package app.futured.brmo23.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.futured.brmo23.ConfirmationDialog
import app.futured.brmo23.ConfirmationSheet
import app.futured.brmo23.DetailScreen
import app.futured.brmo23.DetailViewState
import app.futured.brmo23.SlotChild
import app.futured.brmo23.SlotDestination
import app.futured.brmo23.android.ui_tooling.Showcase
import app.futured.brmo23.android.ui_tooling.Spacer
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

@Composable
fun DetailScreenUi(
    screen: DetailScreen,
    modifier: Modifier = Modifier
) {
    val viewState by screen.state.subscribeAsState()
    val slot by screen.slot.subscribeAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(viewState.text, style = MaterialTheme.typography.titleLarge)
        Text("Dialog result: ${viewState.dialogResult}")
        Text("Sheet result: ${viewState.sheetResult}")

        Spacer(size = 16.dp)
        Button(onClick = screen.actions::openAnotherDetailClicked) {
            Text(text = "Open another detail")
        }

        Spacer(8.dp)
        OutlinedButton(onClick = screen.actions::openConfirmationDialogClicked) {
            Text(text = "Confirmation dialog")
        }

        Spacer(8.dp)
        OutlinedButton(onClick = screen.actions::openConfirmationSheetClicked) {
            Text(text = "Confirmation sheet")
        }
    }

    slot.child?.let { slotChild ->
        DetailSlotOverlay(child = slotChild.instance)
    }
}

@Composable
private fun DetailSlotOverlay(child: SlotChild) {
    when (child) {
        is ConfirmationDialog -> ConfirmationDialog(dialog = child)
        is ConfirmationSheet -> ConfirmationSheet(sheet = child)
    }
}

@Preview
@Composable
fun DetailScreenUiPreview() {
    val screenStub = object : DetailScreen {
        override val state: Value<DetailViewState> = MutableValue(DetailViewState(text = "Hello from Preview"))
        override val actions: DetailScreen.Actions = object : DetailScreen.Actions {}
        override val slot: Value<ChildSlot<SlotDestination, SlotChild>> = MutableValue(ChildSlot(null))
    }

    Showcase {
        DetailScreenUi(screen = screenStub, modifier = Modifier.fillMaxSize())
    }
}
