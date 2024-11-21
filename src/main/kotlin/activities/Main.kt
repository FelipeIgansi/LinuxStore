package activities

import activities.constants.Constants
import activities.controller.MainController
import activities.packageManager.AptCommandExecutor
import activities.packageManager.PackageService
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import navigation.Routes


fun main() = application {
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
  val aptCommandExecutor = AptCommandExecutor()
  val packageService = PackageService
  val mainController = MainController(aptCommandExecutor = aptCommandExecutor, packageService = packageService)
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
    scaffold(
      mainController = mainController,
      route = Routes.Home.route,
      buttonList = buttonMenuItems,
    )
  }
}
