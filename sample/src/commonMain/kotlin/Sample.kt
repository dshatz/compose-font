import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dshatz.composefonts.loadFont
import compose_fonts.sample.generated.resources.Res
import compose_fonts.sample.generated.resources.rubik_var

@Composable
fun Sample() {
    var weight by remember { mutableStateOf(300f) }
    val fontFamily by loadFont(Res.font.rubik_var, FontWeight(weight.toInt()))
    Column {
        Text("Weight: $weight")
        Slider(weight, valueRange = 300f..900f, onValueChange = {weight = it}, modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp))
        Text("The quick brown fox jumps over the lazy dog", fontFamily = fontFamily)
    }

}