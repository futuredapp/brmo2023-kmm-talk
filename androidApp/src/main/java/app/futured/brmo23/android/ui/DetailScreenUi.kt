package app.futured.brmo23.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.futured.brmo23.android.ui_tooling.Showcase
import app.futured.brmo23.navigation_decompose.DetailScreen
import app.futured.brmo23.navigation_decompose.DetailViewState
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

@Composable
fun DetailScreenUi(
    screen: DetailScreen,
    modifier: Modifier = Modifier
) {
    val viewState by screen.state.subscribeAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(viewState.text)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = screen.actions::openAnotherDetailClicked) {
            Text(text = "Open another detail")
        }
    }
}

@Preview
@Composable
fun DetailScreenUiPreview() {
    val screenStub = object : DetailScreen {
        override val state: Value<DetailViewState> = MutableValue(DetailViewState(text = "Hello from Preview"))
        override val actions: DetailScreen.Actions = object : DetailScreen.Actions {}
    }

    Showcase {
        DetailScreenUi(screen = screenStub, modifier = Modifier.fillMaxSize())
    }
}
