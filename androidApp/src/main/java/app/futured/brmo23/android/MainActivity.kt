package app.futured.brmo23.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import app.futured.brmo23.android.navigation.BrMo23App
import app.futured.brmo23.android.theme.Brmo23Theme
import app.futured.brmo23.navigation_decompose.RootNavigationComponent
import com.arkivanov.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val componentContext = defaultComponentContext()
        setContent {
            Brmo23Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BrMo23App(component = RootNavigationComponent(componentContext))
                }
            }
        }
    }
}
