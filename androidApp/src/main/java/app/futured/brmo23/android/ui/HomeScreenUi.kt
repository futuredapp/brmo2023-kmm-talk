package app.futured.brmo23.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.futured.brmo23.android.ui_tooling.Showcase
import app.futured.brmo23.navigation_decompose.HomeScreen

@Composable
fun HomeScreenUi(
    screen: HomeScreen,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = screen.actions::openDetailClicked) {
            Text(text = "Open detail")
        }
    }
}

@Preview
@Composable
fun HomeScreenUiPreview() {
    val screenStub = object : HomeScreen {
        override val actions: HomeScreen.Actions = object : HomeScreen.Actions {}
    }

    Showcase {
        HomeScreenUi(screen = screenStub, modifier = Modifier.fillMaxSize())
    }
}
