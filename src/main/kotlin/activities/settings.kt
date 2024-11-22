@file:OptIn(ExperimentalMaterialApi::class)

package activities

import activities.components.Banner
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun settings() {
  Column {
    Banner.home()
    Spacer(Modifier.height(10.dp))

    Text(
      "Explore os mais baixados!",
      color = Color.White,
      modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 10.dp),
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold
    )
  }
}
