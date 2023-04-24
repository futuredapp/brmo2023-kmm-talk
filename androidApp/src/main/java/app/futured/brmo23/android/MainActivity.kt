package app.futured.brmo23.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import app.futured.brmo23.android.navigation.ComposeNavigationStackDemo
import app.futured.brmo23.android.navigation.SharedNavigationStackDemo
import app.futured.brmo23.android.theme.Brmo23Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Brmo23Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SharedNavigationStackDemo(Modifier.fillMaxSize())
                }
            }
        }
    }
}
