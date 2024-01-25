package br.com.dictionmaster.presentation.others

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import br.com.dictionmaster.R

@Composable
fun MultiColorText(
    vararg textWithColors: Pair<String, Color>,
    modifier: Modifier,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    textAlign: TextAlign
) {
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
        textAlign = textAlign
    )
}

@Preview(showBackground = true)
@Composable
fun MultiColorTextPreview() {
    MultiColorText(
        Pair("Try 7 Days Free", Color.Black),
        Pair(", then only ", Color.LightGray),
        Pair("\$19,99 ", Color.Black),
        Pair("per year. Cancel anytime.", Color.LightGray),
        modifier = Modifier.padding(32.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextChipWithIcon(
    text: String,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.mipmap.flag),
            contentDescription = null,
            modifier = Modifier.padding(
                start = 8.dp,
                top = 8.dp,
                bottom = 8.dp
            )
        )
        Text(
            text = text,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 8.dp,
                bottom = 8.dp,
                end = 16.dp
            ),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextChipWithIconPreview() {
    TextChipWithIcon(
        text = stringResource(id = R.string.english).uppercase(),
        modifier = Modifier
            .padding(32.dp)
            .background(
                color = Color.LightGray,
                shape = CircleShape
            )
            .clip(shape = CircleShape)
            .padding(4.dp)
    )
}