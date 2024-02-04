package presentation.character

import presentation.AppColors
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
import datasource.RickAndMortyCharacterDTO

@Composable
fun ComposeCharacterCard(
    character: RickAndMortyCharacterDTO
) {

    Column(
        modifier = Modifier.fillMaxWidth().height(100.dp).background(AppColors.gray),
    ) {
        Text(
            text = character.name,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}
