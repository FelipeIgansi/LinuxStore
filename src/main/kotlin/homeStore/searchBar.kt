package homeStore

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.lightPurple
import theme.primaryColor

@Composable
fun searchBar(text: String, onValueChange: (String) -> Unit) {
  TextField(
    value = text,
    onValueChange = { onValueChange(it) },
    placeholder = { Text("Busque um programa", color = Color.White) },
    shape = RoundedCornerShape(10.dp),
    colors = TextFieldDefaults.outlinedTextFieldColors(
      unfocusedBorderColor = primaryColor,
      focusedBorderColor = lightPurple,
      focusedLabelColor = Color.Blue,
      trailingIconColor = lightPurple,
      textColor = lightPurple
    ),
    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
    singleLine = true,
    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
  )
}
