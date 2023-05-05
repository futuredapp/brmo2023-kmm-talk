package app.futured.brmo23.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import app.futured.brmo23.android.theme.Brmo23Theme
import app.futured.brmo23.android.ui.RootNavigationUi
import app.futured.brmo23.navigation_decompose.RootNavigation
import app.futured.brmo23.navigation_decompose.RootNavigationComponent
import com.arkivanov.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponentContext = defaultComponentContext()
        val rootNavigation: RootNavigation = RootNavigationComponent(rootComponentContext)

        setContent {
            Brmo23Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavigationUi(navigation = rootNavigation)
                }
            }
        }
    }
}
