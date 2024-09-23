package homeStore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.primaryColor

@Composable
fun bannerRed() {
  val gradientColors = listOf(
    darkOrange,
    orange
  )

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(200.dp)
      .padding(start = 20.dp, end = 20.dp)
      .clip(shape = RoundedCornerShape(10.dp))
      .background(brush = Brush.horizontalGradient(colors = gradientColors)),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround
  ) {
    Column {
      Text("All Snap categories", fontWeight = FontWeight.Bold, color = Color.White)
      Text("Our featured apps", color = Color.White)
    }
    Row {
      Image(
        painter = painterResource(resourcePath = "icons/slack.png"),
        contentDescription = null,
        modifier = Modifier.size(50.dp)
      )
      Image(
        painter = painterResource(resourcePath = "icons/spotify.png"),
        contentDescription = null,
        modifier = Modifier.size(50.dp)
      )
      Image(
        painter = painterResource(resourcePath = "icons/vscode.png"),
        contentDescription = null,
        modifier = Modifier.size(50.dp)
      )
    }
  }
}
        .background(primaryColor),