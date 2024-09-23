package homeStore

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import theme.primaryColor

@ExperimentalMaterialApi
@Composable
fun navigationRail(
  showCategoriesOptions: Boolean,
  showSearchTextField: Boolean,
  onShowCategoriesOptionsChanged: (Boolean) -> Unit,
  onShowSearchTextFieldChanged: (Boolean) -> Unit,
) {

  var selected by remember { mutableStateOf(0) }
  val navigationItems = listOf("Home", "Settings", "Updates", "About")
  val icons = listOf(
    painterResource(resourcePath = "icons/home.png"),
    painterResource(resourcePath = "icons/settings.png"),
    painterResource(resourcePath = "icons/update.png"),
    painterResource(resourcePath = "icons/about.png"),
  )
  NavigationRail(
    backgroundColor = primaryColor,
    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 5.dp).clip(RoundedCornerShape(10.dp))
  ) {
    Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.SpaceBetween,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Column {
        IconButton(
          onClick = {
            onShowCategoriesOptionsChanged(!showCategoriesOptions)
            onShowSearchTextFieldChanged(false)
          },
        ) {
          Icon(
            painterResource(resourcePath = "icons/categories.png"),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
          )
        }
        IconButton(onClick = {
          onShowSearchTextFieldChanged(!showSearchTextField)
          onShowCategoriesOptionsChanged(false)
        }) {
          Icon(
            painterResource(resourcePath = "icons/search.png"),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
          )
        }
      }
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
}