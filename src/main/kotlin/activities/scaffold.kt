package activities

import activities.components.navigationRail
import activities.components.searchBar
import activities.components.topMenuCategoryItem
import activities.controller.MainController
import activities.theme.blackBackground
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import navigation.Routes

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun scaffold(
  mainController: MainController,
  route: String,
  buttonList: List<String>
) {
  var textSearch by remember { mutableStateOf("") }

  var showCategoriesOptions by remember { mutableStateOf(true) }
  var showSearchTextField by remember { mutableStateOf(false) }
  var currRoute by remember { mutableStateOf(route) }

  MaterialTheme {
    Scaffold(backgroundColor = blackBackground) {
      Box(Modifier.fillMaxSize()) {
        Row {
          navigationRail(
            showCategoriesOptions = showCategoriesOptions,
            showSearchTextField = showSearchTextField,
            onShowCategoriesOptionsChanged = { newValue -> showCategoriesOptions = newValue },
            onShowSearchTextFieldChanged = { newValue -> showSearchTextField = newValue },
            onButtonClick = { newDestination -> currRoute = newDestination.lowercase() },
            route = route
            )

          Column {
            Row(
              modifier = Modifier.fillMaxWidth().padding(5.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              if (showCategoriesOptions) {
                LazyRow(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(start = 10.dp)
                ) {
                  buttonList.forEach { button ->
                    item {
                      topMenuCategoryItem(text = button)
                      Spacer(Modifier.width(5.dp))
                    }
                  }
                }
              }
              if (showSearchTextField) searchBar(textSearch) { textSearch = it }
            }

            when (currRoute) {
              Routes.Home.route -> listPackages(mainController)
              Routes.Settings.route -> settings()
            }
          }
        }
      }
    }
  }
}