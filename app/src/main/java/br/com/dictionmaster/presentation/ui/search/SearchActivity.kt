package br.com.dictionmaster.presentation.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.dictionmaster.R
import br.com.dictionmaster.presentation.others.TextChipWithIcon
import br.com.dictionmaster.presentation.theme.ButtonColor
import br.com.dictionmaster.presentation.theme.DictionMasterTheme
import br.com.dictionmaster.presentation.ui.result.ResultActivity

class SearchActivity : ComponentActivity() {

    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            context = LocalContext.current
            DictionMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen()
                }
            }
        }
    }

    @Composable
    fun Screen() {
        Scaffold(
            content = {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(it)
                ) {
                    val (imageFlag, textFieldWord, buttonSearch) = createRefs()
                    val createGuideline = createGuidelineFromTop(0.3f)
                    val shape = CircleShape

                    TextChipWithIcon(
                        text = stringResource(id = R.string.english).uppercase(),
                        modifier = Modifier
                            .constrainAs(imageFlag) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(
                                vertical = 32.dp
                            )
                            .background(
                                color = LightGray,
                                shape = shape
                            )
                            .clip(shape = shape)
                            .padding(4.dp)
                    )

                    var text by remember {
                        mutableStateOf("")
                    }
                    val placeholder = "Type a word..."

                    BasicTextField(
                        value = text,
                        onValueChange = { newText -> text = newText },
                        modifier = Modifier
                            .constrainAs(textFieldWord) {
                                top.linkTo(createGuideline)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        textStyle = TextStyle(
                            color = Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 80.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                if (text.isEmpty()) {
                                    Text(
                                        text = placeholder,
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Light,
                                        color = LightGray
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )

                    Button(
                        onClick = {
                            val intent = newInstance(
                                context = context,
                                word = text
                            )
                            startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .constrainAs(buttonSearch) {
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
                            text = stringResource(id = R.string.search).uppercase(),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        )
    }

    companion object {

        private const val WORD = "word"
        fun newInstance(
            context: Context,
            word: String? = null
        ): Intent {
            return Intent(context, ResultActivity::class.java).apply {
                putExtra(WORD, word)
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun SearchActivityPreview() {
        DictionMasterTheme {
            Screen()
        }
    }
}