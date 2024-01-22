package br.com.dictionmaster.presentation.ui.purchase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.dictionmaster.R
import br.com.dictionmaster.network.extensions.onBackButtonPressed
import br.com.dictionmaster.presentation.theme.ButtonColor
import br.com.dictionmaster.presentation.theme.DictionMasterTheme

class PurchaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionMasterTheme {
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
            content = { paddingValues ->
                ConstraintLayout(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    val (purchaseImage, imageLauncher, imageTitle, titleText, subtitleText, subscribeButton) = createRefs()

                    Image(
                        painterResource(id = R.mipmap.purchase),
                        contentDescription = null,
                        modifier = Modifier.constrainAs(purchaseImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )

                    Image(
                        painterResource(id = R.mipmap.icon),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .constrainAs(imageLauncher) {
                                top.linkTo(purchaseImage.top, margin = 320.dp)
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

                    MultiColorText(
                        Pair("Subscribe now to get ", Black),
                        Pair("unlimited ", ButtonColor),
                        Pair("searches and full access to ", Black),
                        Pair("all features", ButtonColor),
                        Pair(".", Black),
                        modifier = Modifier.constrainAs(titleText) {
                            top.linkTo(imageTitle.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                            .padding(
                                start = 32.dp,
                                end = 32.dp
                            ),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    MultiColorText(
                        Pair("Try 7 Days Free", Black),
                        Pair(", then only ", LightGray),
                        Pair("\$19,99 ", Black),
                        Pair("per year. Cancel anytime.", LightGray),
                        modifier = Modifier.constrainAs(subtitleText) {
                            top.linkTo(titleText.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                            .padding(
                                top = 18.dp,
                                start = 32.dp,
                                end = 32.dp
                            ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )

                    Button(
                        onClick = {
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .constrainAs(subscribeButton) {
                                top.linkTo(subtitleText.bottom)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(
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
                            text = stringResource(id = R.string.subscribe).uppercase(),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        )
    }

    @Composable
    fun MultiColorText(vararg textWithColors: Pair<String, Color>, modifier: Modifier, fontSize: TextUnit, fontWeight:FontWeight) {
        Text(
            modifier = modifier,
            text = buildAnnotatedString {
                textWithColors.forEach { (text, color) ->
                    withStyle(style = SpanStyle(color = color)) {
                        append(text)
                    }
                }
            },
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PurchaseActivityPreview() {
        Screen()
    }
}

