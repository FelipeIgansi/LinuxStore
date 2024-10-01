package activities

import activities.components.*
import activities.theme.blackBackground
import activities.theme.primaryColor
import activities.viewmodel.MainController
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

//  val buttonMenuItems = listOf("Explore", "Productivity", "Development", "Games", "Art & Design")
  val buttonMenuItems = listOf("video")

  val packageService = PackageService

  var textSearch by remember { mutableStateOf("") }
  var page by remember { mutableIntStateOf(1) }
  val size by remember { mutableIntStateOf(10) }

  val packageList by mainController.packageList.collectAsState()
  val packageSize by aptCommandExecutor.packageSize.collectAsState()

  LaunchedEffect(page) {
    mainController.setPackageList(packageService.listPackagesBySection(aptCommandExecutor, textSearch, page, size))
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
                LazyRow(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 10.dp)) {
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
                  mainController.setPackageList(
                    packageService.listPackagesBySection(
                      aptCommandExecutor,
                      textSearch,
                      page,
                      size
                    )
                  )
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

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
              LazyRow(modifier = Modifier.clip(CircleShape)) {
                for (i in 1..<(packageSize / size) / 2) {// esse dividido por 2 ai é só para diminuir o numero de paginas
                  item {
                    buttonPages(i, page){
                      page = i
                    }
                  }
                }
              }
            }

            Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
              LazyVerticalGrid(
                columns = GridCells.Fixed(2),
              ) {
                packageList.forEach {
                  item {
                    verticalListProgramsItems(
                      aptPackageModel = it,
                      /*onDownloadClicked = {
                        //TODO()
                      }*/
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

@Composable
private fun buttonPages(buttonText: Int, currPage: Int, onButtonClicked: () -> Unit) {
  Button(
    onClick = { onButtonClicked() },
    colors = ButtonDefaults.buttonColors(
      backgroundColor = if (currPage == buttonText) primaryColor else Color.Transparent,
      contentColor = Color.White
    )
  ) {
    Text((buttonText).toString())
  }

}


fun main() = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "UbuntuStore",
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
