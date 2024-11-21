package activities

import activities.components.Banner
import activities.components.packageItemView
import activities.controller.MainController
import activities.controller.PackageInstallationController
import activities.theme.primaryColor
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun listPackages(
  mainController: MainController
) {
  val packageMap = mutableMapOf<AptPackageModel, String>()

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

  val listPackages by mainController.packageList.collectAsState()
  var isLoading by remember { mutableStateOf(true) }

  LaunchedEffect(Unit) {
    popularApps.forEach { packageName ->
      mainController.buscaTodosOsPacotesAPT(packageName, packageMap)
    }
    isLoading = false
  }


  Column {
    Banner.home()

    Spacer(Modifier.height(10.dp))

    Text(
      "Explore os mais baixados!",
      color = Color.White,
      modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 10.dp),
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold
    )

    if (isLoading) {
      Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
          modifier = Modifier.size(50.dp),
          backgroundColor = Color.Gray,
          color = primaryColor,
          strokeWidth = 2.dp,
        )
      }
    } else {
      Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
        LazyVerticalGrid(
          columns = GridCells.Fixed(2),
        ) {
          listPackages.forEach { (key, icon) ->
            item {
              packageItemView(
                packageData = key,
                packageIconPath = icon,
                packageInstallationController = PackageInstallationController()
              )
            }
          }
        }
      }
    }
  }
}
