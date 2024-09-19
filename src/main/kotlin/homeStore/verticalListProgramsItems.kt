package homeStore

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun verticalListProgramsItems(programsMock: String, iconProgramMock: String) {
  Row(
    modifier = Modifier.width(200.dp)
      .padding(5.dp)
      .height(150.dp)
      .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
      .padding(start = 20.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Start
  ) {
    Image(
      painter = painterResource(resourcePath = "icons/$iconProgramMock"),
      contentDescription = null,
      modifier = Modifier.size(50.dp)
    )
    Spacer(Modifier.width(10.dp))
    Column {
      Text(programsMock, color = Color.White)
      Text("empresa", color = Color.White)
      Text("Descrição", color = Color.White)
      Row {
        for (i in 0..4) Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
      }
    }
  }
}