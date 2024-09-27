package activities.components

import activities.theme.blackBackground
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun navigationRail() {

  var selected by remember { mutableStateOf(0) }
  val navigationItems = listOf("Home", "Settings", "Updates", "About")
  val icons = listOf(
    painterResource(resourcePath = "icons/home.png"),
    painterResource(resourcePath = "icons/settings.png"),
    painterResource(resourcePath = "icons/update.png"),
    painterResource(resourcePath = "icons/about.png"),
  )
  NavigationRail(
    backgroundColor = blackBackground,
    modifier = Modifier.clip(RoundedCornerShape(10.dp))
  ) {
    Column {
      navigationItems.forEachIndexed { index, item ->
        NavigationRailItem(
          icon = {
            Icon(
              icons[index],
              contentDescription = item,
              tint = Color.White,
              modifier = Modifier.size(20.dp)
            )
          },
          label = { Text(item) },
          selected = selected == index,
          onClick = { selected = index },
          selectedContentColor = Color.White,
          unselectedContentColor = Color.Gray,
          alwaysShowLabel = selected == index
        )
      }
    }
  }
}