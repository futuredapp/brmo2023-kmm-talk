package app.futured.brmo23.android.ui_tooling

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import app.futured.brmo23.android.theme.Brmo23Theme

/**
 * Wraps content in a app theme and surface with background color.
 */
@Composable
fun Showcase(content: @Composable () -> Unit) {
    Brmo23Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}
