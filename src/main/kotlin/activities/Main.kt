package activities

import activities.components.*
import activities.controller.MainController
import activities.controller.ProgramListItemController
import activities.theme.blackBackground
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun app(
  mainController: MainController,
  aptCommandExecutor: AptCommandExecutor
) {
  val menuItem = Constants.MenuItems.EN

  val buttonMenuItems = listOf(
    menuItem.OFFICE,
    menuItem.PRODUCTIVITY,
    menuItem.DEVELOPMENT,
    menuItem.GAMES,
    menuItem.GRAPHICS,
    menuItem.MULTIMEDIA,
    menuItem.INTERNET
  ).sorted()

  val packageService = PackageService

  var textSearch by remember { mutableStateOf("") }

  val packageList by mainController.packageList.collectAsState()
  val packageListTemp = mutableMapOf<AptPackageModel, String>()

  val popularApps = listOf(
    "gimp",
    "chromium-browser",
    "vlc",
    "libreoffice",
    "filezilla",
    "inkscape",
    "transmission-gtk",
    "audacity",
    "thunderbird",
    "shotwell",
    "blender",
    "krita",
    "steam-installer",
    "obs-studio"
  )

  LaunchedEffect(Unit) {
    popularApps.forEach { packageName ->
      val key = packageService.getPackageDetails(aptCommandExecutor, packageName)
      packageListTemp[key] = "icons/$packageName.svg"
    }
  }

  LaunchedEffect(Unit) {
    if (packageListTemp.keys.size > 0) mainController.setPackageList(packageListTemp)
  }

  var showCategoriesOptions by remember { mutableStateOf(true) }
  var showSearchTextField by remember { mutableStateOf(false) }

  MaterialTheme {
    Surface(color = blackBackground) {
      Box(Modifier.fillMaxSize()) {
        Row {
          navigationRail(
            showCategoriesOptions = showCategoriesOptions,
            showSearchTextField = showSearchTextField,
            onShowCategoriesOptionsChanged = { newValue -> showCategoriesOptions = newValue },
            onShowSearchTextFieldChanged = { newValue -> showSearchTextField = newValue }
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
                  buttonMenuItems.forEach { button ->
                    item {
                      topMenuCategoryItem(text = button)
                      Spacer(Modifier.width(5.dp))
                    }
                  }
                }
              }
              if (showSearchTextField) searchBar(textSearch, { newText -> textSearch = newText },
                {
                  /*mainController.setPackageList(
                    packageService.listPackagesBySection(
                      aptCommandExecutor,
                      textSearch
                    )
                  )*/
                }
              )
            }

            Banner().home()

            Spacer(Modifier.height(10.dp))

            Text(
              "Explore os mais baixados!",
              color = Color.White,
              modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 10.dp),
              textAlign = TextAlign.Center,
              fontWeight = FontWeight.Bold
            )

            Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
              LazyVerticalGrid(
                columns = GridCells.Fixed(2),
              ) {
                packageList.forEach { (key, value) ->
                  item {
                    verticalListProgramsItems(
                      aptPackageModel = key,
                      iconPath = value,
                      controller = ProgramListItemController()
                    )
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

fun main() = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "Store",
    alwaysOnTop = false,
    state =
    WindowState(
      width = 700.dp,
      height = 600.dp,
      placement = WindowPlacement.Maximized,
      position = WindowPosition(Alignment.Center)
    ),
  ) {
    app(
      MainController(),
      AptCommandExecutor()
    )
  }
}
