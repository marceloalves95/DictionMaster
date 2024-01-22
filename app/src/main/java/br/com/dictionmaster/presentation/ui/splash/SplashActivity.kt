package br.com.dictionmaster.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.dictionmaster.presentation.ui.search.SearchActivity
import br.com.dictionmaster.R
import br.com.dictionmaster.presentation.theme.DictionMasterTheme
import br.com.dictionmaster.presentation.theme.MyCustomFont

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

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
                    val (imageLauncher, imageTitle, nameText) = createRefs()
                    val topGuideline = createGuidelineFromTop(0.5f)

                    Image(
                        painterResource(id = R.mipmap.icon),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .constrainAs(imageLauncher) {
                                bottom.linkTo(topGuideline)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    Image(
                        painterResource(id = R.mipmap.title),
                        contentDescription = null,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .constrainAs(imageTitle) {
                                top.linkTo(imageLauncher.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(
                        text = stringResource(id = R.string.my_name),
                        fontFamily = MyCustomFont,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .constrainAs(nameText) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(bottom = 42.dp)
                    )
                }

                LaunchedEffect(key1 = true) {
                    object : CountDownTimer(2000, 200) {
                        override fun onTick(millisUntilFinished: Long) {

                        }

                        override fun onFinish() {
                            val intent = newInstance(context)
                            startActivity(intent)
                            finish()
                        }
                    }.start()
                }
            }
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun SplashScreenPreview() {
        DictionMasterTheme {
            Screen()
        }
    }

    companion object {
        fun newInstance(
            context: Context
        ): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }
}

