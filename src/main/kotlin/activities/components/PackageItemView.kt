package activities.components

import activities.AptPackageModel
import activities.controller.PackageInstallationController
import activities.theme.backgroundListItems
import activities.theme.lightPurple
import activities.theme.primaryColor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun packageItemView(
  packageData: AptPackageModel,
  packageIconPath: String,
  packageInstallationController: PackageInstallationController
) {
  var isItemHovered by remember { mutableStateOf(false) }
  var isButtonHovered by remember { mutableStateOf(false) }

  val isProgressBarVisible by packageInstallationController.isProgressBarVisible.collectAsState()
  val iconButton by packageInstallationController.iconButton.collectAsState()
  val percent by packageInstallationController.percent.collectAsState()

  val buttonBorderColor = if (iconButton == "download.png") primaryColor else Color.Red
  val buttonBackgroundColor = if (iconButton == "download.png") primaryColor else Color.Red
  val buttonIconColor = Color.White
  val itemBorder = if (isItemHovered) lightPurple else Color.Transparent
  val cornerRadius = 10.dp



  LaunchedEffect(Unit) {
    packageInstallationController.updateIconButton(packageData)
  }

  Row(
    modifier = Modifier.width(200.dp)
      .padding(5.dp)
      .height(150.dp)
      .border(
        BorderStroke(width = 3.dp, color = itemBorder),
        shape = RoundedCornerShape(cornerRadius)
      )
      .clip(RoundedCornerShape(cornerRadius))
      .background(backgroundListItems)
      .padding(start = 20.dp)
      .onPointerEvent(PointerEventType.Enter) { isItemHovered = true }
      .onPointerEvent(PointerEventType.Exit) { isItemHovered = false },
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Start
  ) {
    Image(
      painter = painterResource(resourcePath = packageIconPath),
      contentDescription = null,
      modifier = Modifier.size(50.dp)
    )
    Spacer(Modifier.width(cornerRadius))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          packageData.packageName.capitalize(Locale.current),
          color = Color.White
        )
        Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
          Text(packageData.description, color = Color.Gray, maxLines = 1)
          Row {
            repeat(5) {
              Icon(Icons.Default.Star, contentDescription = null, tint = Color.LightGray)
            }
          }
        }
      }
      if (isProgressBarVisible) {
        CircularProgressIndicator(
          progress = percent.toFloat() / 100,
          modifier = Modifier.size(50.dp),
          color = primaryColor,
          backgroundColor = Color.Gray,
        )
      } else {
        OutlinedButton(
          onClick = {
            packageInstallationController.updateCurrentPackageState(packageData)
            packageInstallationController.executeInstallation()
          },
          shape = RoundedCornerShape(cornerRadius),
          border = BorderStroke(
            width = 3.dp,
            color = buttonBorderColor
          ),
          colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isButtonHovered) buttonBackgroundColor else Color.Transparent,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = Color.White
          ),
          modifier = Modifier.size(50.dp)
            .onPointerEvent(PointerEventType.Enter) { isButtonHovered = true }
            .onPointerEvent(PointerEventType.Exit) { isButtonHovered = false },
        ) {
          Icon(
            painter = painterResource("icons/$iconButton"), contentDescription = null,
            tint = buttonIconColor,
            modifier = Modifier.size(30.dp)
          )
        }
      }
      Spacer(Modifier.width(20.dp))
    }
  }
}