package activities.components

import activities.AptPackageModel
import activities.theme.backgroundListItems
import activities.theme.lightPurple
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun verticalListProgramsItems(
  aptPackageModel: AptPackageModel,
  iconPath: String
//  onDownloadClicked: ()->Unit
) {
  var isHover by remember { mutableStateOf(false) }
  Row(
    modifier = Modifier.width(200.dp)
      .padding(5.dp)
      .height(150.dp)
      .border(BorderStroke(width = 1.dp, color = if (isHover) lightPurple else Color.Transparent))
      .clip(RoundedCornerShape(10.dp))
      .background(backgroundListItems)
      .padding(start = 20.dp)
      .onPointerEvent(PointerEventType.Enter) { isHover = true }
      .onPointerEvent(PointerEventType.Exit) { isHover = false },
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Start
  ) {
    Image(
      painter = painterResource(resourcePath = iconPath),
      contentDescription = null,
      modifier = Modifier.size(50.dp)
    )
    Spacer(Modifier.width(10.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          aptPackageModel.packageName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
          color = Color.White
        )
        Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
          Text(aptPackageModel.description, color = Color.Gray, maxLines = 1)
          Row {
            repeat(5) {
              Icon(Icons.Default.Star, contentDescription = null, tint = Color.LightGray)
            }
          }
        }
      }
      OutlinedButton(
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 3.dp, color = Color.Gray),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        modifier = Modifier.size(50.dp)
      ) {
        Icon(
          painter = painterResource("icons/download.png"), contentDescription = null,
          tint = Color.Gray,
          modifier = Modifier.size(30.dp)
        )
      }
      Spacer(Modifier.width(20.dp))
    }
  }
}