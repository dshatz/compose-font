package com.dshatz.composefonts

import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Typeface
import compose_fonts.lib.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.skia.Data
import org.jetbrains.skia.FontVariation
import org.jetbrains.skia.Typeface

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun loadFont(
    path: String,
    fontWeight: FontWeight
): State<FontFamily> {
    val loaded by produceState(Typeface.makeDefault(), path) {
        value = Typeface
            .makeFromData(
                Data.makeFromBytes(Res.readBytes(path))
            )
    }
    return derivedStateOf {
        val cloned = loaded
            .makeClone(fontWeight)
        FontFamily(typeface = Typeface(cloned, "alias${fontWeight.weight}"))
    }
}

private fun Typeface.makeClone(weight: FontWeight): Typeface {
    return makeClone(FontVariation("wght", weight.weight.toFloat()))
}

private fun loadFont(
    data: ByteArray,
    fontWeight: FontWeight
): FontFamily {
    return Typeface.makeFromData(Data.makeFromBytes(data))
            .makeClone(fontWeight).run {
                FontFamily(typeface = Typeface(this, "alias${fontWeight.weight}"))
        }
}

@Composable
actual fun loadFont(
    fontResource: FontResource,
    fontWeight: FontWeight
): State<FontFamily> {
    val bytes by loadFontBytes(fontResource)
    return derivedStateOf {
        bytes?.let { loadFont(it, fontWeight) } ?: FontFamily.Default
    }
}