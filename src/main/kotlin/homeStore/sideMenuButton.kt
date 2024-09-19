package homeStore

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import theme.orange

@Composable
fun sideMenuButton(buttonMenuItems: String, iconsMenuItems: String) {
  OutlinedButton(
    onClick = {},
    modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
    contentPadding = PaddingValues(start = 5.dp),
    colors = ButtonDefaults.outlinedButtonColors(
      backgroundColor = orange,
      contentColor = Color.White
    )
  ) {
    Row(Modifier.fillMaxWidth(),/* horizontalArrangement = Arrangement.Start*/) {
      /*Icon(
        painter = painterResource(resourcePath = "icons/$iconsMenuItems"),
        contentDescription = null, modifier = Modifier.size(20.dp)
      )*/
      Spacer(Modifier.width(10.dp))
      Text(buttonMenuItems)
    }
  }
}