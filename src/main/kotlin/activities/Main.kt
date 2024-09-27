package activities

import activities.components.*
import activities.theme.blackBackground
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import java.awt.Dimension
import java.awt.Frame

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
fun main() = application {
  var isOpen by remember { mutableStateOf(true) }
  var windowPlacement by remember { mutableStateOf(WindowPlacement.Floating) }
  var isFindClicked by remember { mutableStateOf(false) }
  var isMaximized by remember { mutableStateOf(windowPlacement == WindowPlacement.Maximized) }

  if (isOpen) {
    val width = 800
    val height = 600
    Window(
      onCloseRequest = { isOpen = false },
      title = "Store",
      alwaysOnTop = false,
      state = WindowState(
        width = width.dp,
        height = height.dp,
        placement = windowPlacement,
        position = WindowPosition(Alignment.Center)
      ),
      undecorated = true,
      transparent = true,
      resizable = true,
      focusable = true,
    ) {
      val buttonMenuItems = listOf("Explore", "Productivity", "Development", "Games", "Art & Design")
      val programsMock = listOf("postman", "Spotify", "Gimp", "Inkscape", "VSCode", "Slack")
      val iconProgramMock = listOf("postman.png", "spotify.svg", "gimp.png", "Inkscape.png", "vscode.png", "slack.png")
      var textSearch by remember { mutableStateOf("") }

      windowPlacement = if (isMaximized) WindowPlacement.Maximized else WindowPlacement.Floating

      window.minimumSize = Dimension(width, height)
      window.maximumSize = Dimension(window.size.width, window.size.height)

      MaterialTheme {
        Surface(
          color = blackBackground,
          shape = RoundedCornerShape(if (!isMaximized) 15.dp else 0.dp)
        ) {
          Box(Modifier.fillMaxSize()) {
            Column {
              WindowDraggableArea(modifier = Modifier.pointerInput(Unit) {
                detectDragGestures(
                  onDragStart = { },
                  onDrag = { _, _ -> },
                  onDragEnd = { isMaximized = false },
                  onDragCancel = {}
                )
              }) {
                Row(
                  modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                  ) {
                    buttonMenuItems.forEach { button ->
                      item {
                        topMenuCategoryItem(text = button)
                        Spacer(Modifier.width(5.dp))
                      }
                    }
                  }


                  Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = { isFindClicked = !isFindClicked }) {
                      Icon(
                        painterResource(resourcePath = "icons/search.png"),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = Color.White
                      )
                    }
                  }


                  Row(modifier = Modifier, horizontalArrangement = Arrangement.End) {
                    windowControlButtons(
                      isMaximized = isMaximized,
                      onMaximizedRequest = { newValue -> isMaximized = newValue },
                      onCloseRequest = { newValue -> isOpen = newValue },
                      window = window
                    )
                  }
                }
              }

              Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
              Row(modifier = Modifier.padding(10.dp)) {
                navigationRail()

                Column {
                  if (isFindClicked) {
                    Row(
                      modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
                      horizontalArrangement = Arrangement.End
                    ) {
                      searchBar(textSearch) { newText ->
                        textSearch = newText
                      }
                    }
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
                      columns = GridCells.Fixed(3),
                      content = {
                        for (i in programsMock.indices) {
                          item {
                            verticalListProgramsItems(
                              programsMock = programsMock[i],
                              iconProgramMock = iconProgramMock[i]
                            )
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
      }
    }
  }
}

@Composable
private fun windowControlButtons(
  isMaximized: Boolean,
  onMaximizedRequest: (Boolean) -> Unit,
  onCloseRequest: (Boolean) -> Unit,
  window: Frame
) {
  IconButton(onClick = { window.state = Frame.ICONIFIED }) {
    Icon(
      painter = painterResource("icons/minimized.png"),
      contentDescription = null,
      tint = Color.White,
      modifier = Modifier.size(30.dp)
    )
  }
  IconButton(onClick = {
    onMaximizedRequest(!isMaximized)
  }) {
    Icon(
      painter = painterResource(
        "icons/${
          if (isMaximized) "floating.png" else "maximized.png"
        }"
      ),
      contentDescription = null, tint = Color.White,
      modifier = Modifier.size(20.dp)
    )
  }
  Spacer(Modifier.width(2.dp))
  IconButton(onClick = { onCloseRequest(false) }) {
    Icon(
      Icons.Default.Close,
      contentDescription = null,
      tint = Color.White,
      modifier = Modifier.size(20.dp)
    )
  }
  Spacer(Modifier.width(10.dp))
}
