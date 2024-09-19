package homeStore

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import theme.orange

@Composable
fun sideMenuButton(buttonMenuItems: String) {
  OutlinedButton(
    onClick = {},
    colors = ButtonDefaults.outlinedButtonColors(
      backgroundColor = orange,
      contentColor = Color.White
    )
  ) {
    Text(buttonMenuItems)
  }
}