import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import datasource.RickAndMortyApi
import datasource.RickAndMortyCharacterDTO
import datasource.RickAndMortyDTO
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import presentation.style.AppColors
import presentation.character.ComposeCharacterCard
import presentation.style.TextStyles

sealed interface UiState {
    data object Loading : UiState
    data class Content(val content: RickAndMortyDTO) : UiState
}

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterialApi::class)
@Composable
fun App() {
    MaterialTheme {

        var uiState by remember {
            mutableStateOf<UiState>(UiState.Loading)
        }

        var characters by remember {
            mutableStateOf(emptyList<RickAndMortyCharacterDTO>())
        }

        val scope = rememberCoroutineScope()

        var launchedEffectKey by remember {
            mutableStateOf(Unit)
        }

        var isRefreshing by remember {
            mutableStateOf(false)
        }

        val refreshState = rememberPullRefreshState(isRefreshing, {
            launchedEffectKey = Unit
        })

        Box(
            modifier = Modifier.fillMaxSize().background(AppColors.background)
                .pullRefresh(refreshState),
            contentAlignment = Alignment.TopCenter
        ) {

            Column {
                ComposeTopBar()

                LaunchedEffect(launchedEffectKey) {
                    scope.launch {
                        characters = RickAndMortyApi().getCharacters().results
                    }
                }

                LazyColumn {
                    items(characters) {
                        ComposeCharacterCard(character = it, modifier = Modifier.padding(10.dp))
                    }
                }
            }

            PullRefreshIndicator(isRefreshing, refreshState)
        }
    }
}

@Composable
private fun ComposeTopBar(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        color = Color.White,
        shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "The Rick and Morty API",
                color = Color.Black,
                modifier = Modifier.padding(10.dp),
                style = TextStyles.title
            )
        }
    }
}