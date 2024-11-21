package activities.components

import activities.theme.lightPurple
import activities.theme.primaryColor
import androidx.compose.foundation.layout.*
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
fun searchBar(text: String, onValueChange: (String) -> Unit, /*onButtonClicked: () -> Unit*/) {
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
    modifier = Modifier.fillMaxWidth()
      .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp),
    trailingIcon = {
      IconButton(onClick = { /*onButtonClicked()*/ }) {
        Icon(
          painterResource(resourcePath = "icons/search.png"),
          contentDescription = null,
          tint = primaryColor,
          modifier = Modifier.size(20.dp)
        )
      }
    }
  )
}
