package activities.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import activities.theme.primaryColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun topMenuCategoryItem(text: String) {
  var isHover by remember { mutableStateOf(false) }
  OutlinedButton(
    onClick = {},
    colors = ButtonDefaults.outlinedButtonColors(
      backgroundColor = if (isHover) primaryColor else Color.Transparent,
      contentColor = Color.White
    ),
    shape = RoundedCornerShape(10.dp),
    modifier = Modifier.onPointerEvent(PointerEventType.Enter) { isHover = true }
                       .onPointerEvent(PointerEventType.Exit) { isHover = false }
  ) {
    Text(text)
  }
}