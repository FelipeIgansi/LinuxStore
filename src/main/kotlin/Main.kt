import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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

@Composable
@Preview
fun app() {

  val buttonMenuItems = listOf("Explore", "Productivity", "Development", "Games", "Art & Design")
  val iconsMenuItems = listOf("explore.png", "productivity.png", "development.png", "games.png", "art.png")

  val programsMock = listOf("postman", "Spotify", "Gimp", "Inkscape", "VSCode", "Slack")
  val iconProgramMock = listOf("postman.png", "spotify.png", "gimp.png", "Inkscape.png", "vscode.png", "slack.png")

  var textSearch by remember { mutableStateOf("") }
  MaterialTheme {
    Box(Modifier.fillMaxSize().background(Color.Black)) {
      Row {
        Column(
          modifier = Modifier.fillMaxHeight().width(200.dp).border(width = 1.dp, color = Color.DarkGray),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.SpaceBetween
        ) {
          Column {
            Text("Menu", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(20.dp))
            for (i in buttonMenuItems.indices) {
              sideMenuButton(buttonMenuItems = buttonMenuItems[i], iconsMenuItems = iconsMenuItems[i])
            }
          }
          Column {
            sideMenuButton("Settings", "settings.png")
            sideMenuButton("Updates", "update.png")
          }
        }

        Column {
          searchBar(textSearch) { newText -> textSearch = newText }

          Spacer(Modifier.height(10.dp))

          bannerRed()

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
              })
          }

        }
      }

    }
  }
}

fun main() = application {
  Window(
    onCloseRequest = ::exitApplication, title = "MyStore", alwaysOnTop = true, state = WindowState(
      width = 700.dp, height = 600.dp, placement = WindowPlacement.Maximized, position = WindowPosition(
        Alignment.Center
      )
    )
  ) {
    app()
  }
}
