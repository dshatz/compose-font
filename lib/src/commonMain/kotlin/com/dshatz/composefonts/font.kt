package com.dshatz.composefonts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import compose_fonts.lib.generated.resources.Res
import org.jetbrains.compose.resources.*

@Composable
expect fun loadFont(path: String, fontWeight: FontWeight): State<FontFamily>


@OptIn(ExperimentalResourceApi::class)
@Composable
fun loadFontBytes(fontResource: FontResource): State<ByteArray?> {
    val env = rememberResourceEnvironment()
    return produceState<ByteArray?>(null) {
        value = getFontResourceBytes(environment = env, fontResource)
    }
}

@Composable
expect fun loadFont(fontResource: FontResource, fontWeight: FontWeight): State<FontFamily>