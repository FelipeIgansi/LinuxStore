package activities.components

import Constants
import activities.AptPackageModel
import activities.theme.backgroundListItems
import activities.theme.lightPurple
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun verticalListProgramsItems(programsMock: String, iconProgramMock: String) {
fun verticalListProgramsItems(
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
    /*Image(
      painter = painterResource(resourcePath = "icons/$iconProgramMock"),
      contentDescription = null,
      modifier = Modifier.size(50.dp)
    )*/
    Spacer(Modifier.width(10.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text("${Constants.PACKAGE_NAME}: ${aptPackageModel.packageName}", color = Color.White)
        Text("${Constants.VERSION_MODEL}: ${aptPackageModel.version}", color = Color.White)
        Text("${Constants.MAINTAINER_MODEL}: ${aptPackageModel.maintainer}", color = Color.White)
        Text("${Constants.DESCRIPTION_MODEL}: ${aptPackageModel.description}", color = Color.White, maxLines = 1)

      }
      IconButton(onClick = {/*onDownloadClicked()*/ }) {
        Icon(
          painter = painterResource("icons/download.png"), contentDescription = null,
          tint = Color.Gray,
          modifier = Modifier.size(55.dp).padding(end = 20.dp)
        )
      }
    }
  }
}