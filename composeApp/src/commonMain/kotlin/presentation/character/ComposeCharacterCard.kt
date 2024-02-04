package presentation.character

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import datasource.RickAndMortyCharacterDTO
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.style.AppColors
import presentation.style.TextStyles

@Composable
fun ComposeCharacterCard(
    modifier: Modifier = Modifier,
    character: RickAndMortyCharacterDTO
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier.fillMaxWidth()
            .wrapContentHeight()
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(5.dp),
        color = AppColors.gray
    ) {

        Column {

            Row(
                horizontalArrangement = Arrangement.Start
            ) {

                KamelImage(
                    asyncPainterResource(
                        character.image
                    ), contentDescription = null,
                    alignment = Alignment.CenterStart,
                    modifier = Modifier.height(100.dp).width(150.dp),
                    contentScale = ContentScale.Crop
                )

                Column {

                    Text(
                        text = character.name,
                        color = Color.White,
                        modifier = Modifier.padding(10.dp),
                        style = TextStyles.title
                    )

                    ComposeStatusIndicator(
                        modifier = Modifier.padding(10.dp),
                        status = character.status,
                        gender = character.gender
                    )
                }
            }

            AnimatedVisibility(isExpanded) {
                Column(modifier = Modifier.padding(10.dp)) {

                    ComposeSection(
                        label = "Last known location:",
                        content = character.location.name
                    )

                    Spacer(Modifier.height(10.dp))

                    ComposeSection(
                        label = "Origin:",
                        content = character.origin.name
                    )
                }
            }
        }
    }
}

@Composable
private fun ComposeSection(
    modifier: Modifier = Modifier,
    label: String,
    content: String
) {

    Column(modifier = modifier) {
        Text(
            text = label,
            style = TextStyles.subTitle,
            color = AppColors.lightGray
        )
        Text(
            text = content,
            style = TextStyles.subTitle,
            color = Color.White
        )
    }

}

@Composable
private fun ComposeStatusIndicator(
    modifier: Modifier = Modifier,
    status: String,
    gender: String
) {

    val statusColor = when (status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> AppColors.lightGray
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            shape = CircleShape, modifier = Modifier.size(10.dp),
            color = statusColor
        ) { }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "$status - $gender",
            style = TextStyles.title.copy(fontSize = 16.sp),
            color = Color.White
        )
    }

}
