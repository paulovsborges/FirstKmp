package character

import AppColors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComposeCharacterCard() {

    Column(
        modifier = Modifier.fillMaxWidth().height(100.dp).background(AppColors.gray),
    ) {
        Text("Character name", color = Color.White, fontSize = 20.sp)
    }
}
