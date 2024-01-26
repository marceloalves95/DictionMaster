package br.com.dictionmaster.presentation.ui.result

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.dictionmaster.R
import br.com.dictionmaster.core.extensions.capitalizeWord
import br.com.dictionmaster.domain.models.Definitions
import br.com.dictionmaster.domain.models.Meanings
import br.com.dictionmaster.domain.models.Search
import br.com.dictionmaster.network.extensions.extra
import br.com.dictionmaster.network.extensions.onBackButtonPressed
import br.com.dictionmaster.presentation.mock.dummySearch
import br.com.dictionmaster.presentation.others.MultiColorText
import br.com.dictionmaster.presentation.theme.ButtonColor
import br.com.dictionmaster.presentation.theme.DictionMasterTheme
import br.com.dictionmaster.presentation.ui.model.DictionMasterState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultActivity : ComponentActivity() {

    private val viewModel: ResultViewModel by viewModel()

    private val word by extra<String>(WORD)
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            context = LocalContext.current
            val state by viewModel.state.collectAsState()
            DictionMasterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        content = { paddingValues ->
                            Column(modifier = Modifier.padding(paddingValues)) {
                                viewModel.searchWord(word)
                                SearchWordState(state = state)
                            }
                        }
                    )
                }
            }
        }
    }

    companion object {
        private const val WORD = "word"
        private const val ONE = 1
    }

    @Composable
    fun SearchWordState(state: DictionMasterState) {
        when (state) {
            is DictionMasterState.ScreenData -> ScreenData(state.search)
            is DictionMasterState.Error -> ScreenError(state.exception)
            else -> Unit
        }
    }

    @Composable
    fun ScreenData(search: List<Search>) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val (phoneticsLazyColumn, divider, titleText, subtitleText, definitionsLazyColumn, newSearchButton) = createRefs()

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(phoneticsLazyColumn) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            ) {
                items(
                    items = search,
                    itemContent = { content ->
                        Text(
                            text = content.word?.capitalizeWord() ?: "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 36.dp,
                                    start = 32.dp,
                                    end = 32.dp
                                ),
                            fontSize = 45.sp,
                            fontFamily = FontFamily(
                                Font(R.font.roboto_condensed_bold)
                            )
                        )

                        var phonetic: String? = ""

                        content.phonetics?.filter { it.audio != "" }?.map {
                            phonetic = it.audio
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 32.dp,
                                    top = 12.dp,
                                    end = 32.dp
                                )
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(ButtonColor),
                                onClick = {
                                    phonetic?.let {
                                        playAudio(
                                            context = context,
                                            audioUrl = it
                                        )
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.audio_speaker_on),
                                    contentDescription = null,
                                    tint = White
                                )
                            }

                            var text = ""
                            content.phonetics?.map {
                                text = it.text.toString()
                            }

                            Text(
                                text = content.phonetic ?: text,
                                modifier = Modifier
                                    .padding(
                                        start = 16.dp,
                                        top = 8.dp,
                                        bottom = 8.dp
                                    ),
                                color = LightGray
                            )
                        }
                    }
                )
            }

            var definition = listOf<Definitions>()
            var meaning = listOf<Meanings>()
            search.map {
                it.meanings?.map { meanings ->
                    definition = meanings.definitions ?: emptyList()
                }
            }
            search.map {
                meaning = it.meanings ?: emptyList()
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp
                    )
                    .constrainAs(definitionsLazyColumn) {
                        height = Dimension.fillToConstraints
                        top.linkTo(phoneticsLazyColumn.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(divider.top)
                    },
            ) {
                items(
                    items = definition,
                    itemContent = { content ->
                        val indexList = definition.indexOf(content).plus(ONE)
                        val partOfSpeech = meaning.map { it.partOfSpeech }

                        MultiColorText(
                            Pair("$indexList) ", Color.Black),
                            Pair("$partOfSpeech ", LightGray),
                            Pair("${content.definition}", Color.Black),
                            modifier = Modifier
                                .padding(
                                    start = 32.dp,
                                    end = 32.dp,
                                    bottom = 12.dp
                                ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start
                        )

                        if (content.example?.isEmpty() == false) {
                            Text(
                                text = "• ${content.example}",
                                modifier = Modifier
                                    .padding(
                                        top = 8.dp,
                                        start = 32.dp,
                                        end = 32.dp,
                                        bottom = 12.dp
                                    ),
                                fontWeight = FontWeight.Light,
                                fontSize = 16.sp
                            )
                        }
                    }
                )
            }

            Divider(
                modifier = Modifier
                    .constrainAs(divider) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(titleText.top)
                    }
                    .padding(bottom = 36.dp),
                color = LightGray, thickness = 1.dp
            )

            val wordSearch = search.map { it.word }.first()

            Text(
                text = "That’s it for \"$wordSearch\"!",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(titleText) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(subtitleText.top)
                    }
                    .padding(
                        bottom = 8.dp,
                        start = 32.dp
                    ),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Text(
                text = stringResource(id = R.string.subtitle),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(subtitleText) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(newSearchButton.top)
                    }
                    .padding(
                        bottom = 16.dp,
                        start = 32.dp,
                        end = 32.dp
                    ),
                fontSize = 16.sp
            )

            Button(
                onClick = {
                    onBackButtonPressed()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(newSearchButton) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(
                        bottom = 32.dp,
                        start = 32.dp,
                        end = 32.dp
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(top = 16.dp, bottom = 16.dp),
                    text = stringResource(id = R.string.new_search).uppercase(),
                    fontSize = 18.sp
                )
            }
        }
    }

    @Composable
    fun ScreenError(exception: Throwable?) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = exception?.message ?: stringResource(id = R.string.error),
                textAlign = TextAlign.Center
            )
        }
    }

    private fun playAudio(context: Context, audioUrl: String) {
        MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(context, Uri.parse(audioUrl))
            prepare()
            start()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ResultActivityPreview() {
        DictionMasterTheme {
            Scaffold(
                content = { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        ScreenData(search = listOf(dummySearch))
                    }
                }
            )
        }
    }
}