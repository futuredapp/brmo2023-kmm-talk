package app.futured.brmo23.android.ui_tooling

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnScope.Spacer(size: Dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun RowScope.Spacer(size: Dp) {
    Spacer(modifier = Modifier.width(size))
}
