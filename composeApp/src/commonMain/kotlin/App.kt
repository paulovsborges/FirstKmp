import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import datasource.RickAndMortyApi
import datasource.RickAndMortyCharacterDTO
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import presentation.AppColors
import presentation.character.ComposeCharacterCard

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {

        var characters by remember {
            mutableStateOf(emptyList<RickAndMortyCharacterDTO>())
        }

        val test = Unit

        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.fillMaxSize().background(AppColors.background)) {

            LazyColumn {
                items(characters) {
                    ComposeCharacterCard(it)
                }
            }

            LaunchedEffect(test) {
                scope.launch {
                    characters = RickAndMortyApi().getCharacters().results
                }
            }
        }
    }
}