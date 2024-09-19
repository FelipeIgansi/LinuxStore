package homeStore

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.orange

@Composable
fun topMenuButton(text: String) {
  OutlinedButton(
    onClick = {},
    colors = ButtonDefaults.outlinedButtonColors(
      backgroundColor = Color.Transparent,
      contentColor = Color.White
    ),
    border = BorderStroke(width = 2.dp, color = orange)
  ) {
    Text(text)
  }
}