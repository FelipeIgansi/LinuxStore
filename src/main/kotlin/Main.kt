import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import homeStore.bannerRed
import homeStore.searchBar
import homeStore.sideMenuButton
import homeStore.verticalListProgramsItems
import theme.blackBackground

@Composable
@Preview
fun app() {

  val buttonMenuItems = listOf("Explore", "Productivity", "Development", "Games", "Art & Design", "Settings", "Updates")

  val programsMock = listOf("postman", "Spotify", "Gimp", "Inkscape", "VSCode", "Slack")
  val iconProgramMock = listOf("postman.png", "spotify.png", "gimp.png", "Inkscape.png", "vscode.png", "slack.png")

  var textSearch by remember { mutableStateOf("") }

  var showLateralMenu by remember { mutableStateOf(false) }

  MaterialTheme {
    Box(Modifier.fillMaxSize().background(blackBackground)) {
      Column {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(
              onClick = { showLateralMenu = !showLateralMenu },
              modifier = Modifier.padding(start = 20.dp, top = 10.dp)
            ) {
              Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
            }
            if (showLateralMenu) {
              LazyRow(modifier = Modifier.padding(start = 20.dp)) {
                item {
                  for (i in buttonMenuItems.indices) {
                    sideMenuButton(buttonMenuItems = buttonMenuItems[i])
                    Spacer(Modifier.width(5.dp))
                  }
                }
              }
            } else searchBar(textSearch) { newText -> textSearch = newText }
          }

          bannerRed()

          Spacer(Modifier.height(10.dp))

          Text(
            "Explore os mais baixados!",
            color = Color.White,
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
          )
          Box(modifier = Modifier.padding(10.dp)) {
            LazyVerticalGrid(
              columns = GridCells.Fixed(2),
              content = {
                for (i in programsMock.indices) {
                  item {
                    verticalListProgramsItems(programsMock = programsMock[i], iconProgramMock = iconProgramMock[i])
                  }
                }
              }
            )
          }
        }
      }
    }
  }
}


fun main() = application {
  Window(
    onCloseRequest = ::exitApplication, title = "UbuntuStore", alwaysOnTop = true, state = WindowState(
      width = 700.dp, height = 600.dp, placement = WindowPlacement.Maximized, position = WindowPosition(
        Alignment.Center
      )
    )
  ) {
    app()
  }
}
