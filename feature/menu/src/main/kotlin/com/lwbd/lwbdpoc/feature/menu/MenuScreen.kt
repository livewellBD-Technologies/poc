package com.lwbd.lwbdpoc.feature.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavOptions
import kotlin.reflect.KFunction1


@Composable
fun MenuRoute(
    onTopicClick: KFunction1<NavOptions?, Unit>,
    modifier: Modifier = Modifier,
    highlightSelectedTopic: Boolean = false,

) {


    MenuScreen(

        modifier = modifier,
    )
}

@Composable
internal fun MenuScreen(

    modifier: Modifier = Modifier,

) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = stringResource(id = R.string.feature_menu_empty_header),
                textAlign = TextAlign.Center
            )
        }

    }
}

