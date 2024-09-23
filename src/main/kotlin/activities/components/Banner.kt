package activities.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import activities.theme.primaryColor

class Banner {
  @Composable
  fun home() {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(start = 15.dp, end = 15.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .background(primaryColor),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceAround
    ) {
      Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
          painter = painterResource(resourcePath = "icons/store.svg"),
          contentDescription = null,
          tint = Color.White,
          modifier = Modifier.size(60.dp)
        )
        Text("All your programs", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 23.sp)
        Text("In one place", color = Color.White)
      }
    }
  }

  @Composable
  fun programs() {
    Box(
      modifier = Modifier
        .height(200.dp)
        .padding(start = 15.dp, end = 15.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .background(primaryColor),
    ) {
      Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Box(modifier = Modifier.border(2.dp, Color.LightGray, shape = RoundedCornerShape(10.dp)).padding(10.dp).background(Color.Transparent)) {
          Image(
            painter = painterResource(resourcePath = "icons/gimp.png"),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
          )
        }
        Spacer(Modifier.width(10.dp))
        Column {
          Text("Gimp", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 23.sp)
          Text("Art & Design. photo and video", color = Color.LightGray)
        }
      }
    }
  }
}