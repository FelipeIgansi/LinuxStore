package activities.components

import activities.theme.lightPurple
import activities.theme.primaryColor
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun searchBar(text: String, onValueChange: (String) -> Unit) {
  OutlinedTextField(
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
    modifier = Modifier.fillMaxWidth(),
    trailingIcon = {
      IconButton(onClick = {}) {
        Icon(
          painterResource(resourcePath = "icons/search.png"),
          contentDescription = null,
          modifier = Modifier.size(20.dp)
        )
      }
    }
  )
}
