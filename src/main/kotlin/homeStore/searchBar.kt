package homeStore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun searchBar(text: String, onValueChange: (String) -> Unit) {
  Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
    OutlinedTextField(
      value = text,
      onValueChange = { onValueChange(it) },
      placeholder = { Text("Busque um programa", color = Color.White) },
      trailingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.White) },
      shape = CircleShape,
      colors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = Color.White,
        focusedBorderColor = Color.Blue,
        focusedLabelColor = Color.Blue,
        trailingIconColor = Color.Blue,
      ),
      modifier = Modifier.fillMaxWidth(),
      textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
      singleLine = true
    )
  }
}