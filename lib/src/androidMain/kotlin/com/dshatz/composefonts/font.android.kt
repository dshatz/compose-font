package com.dshatz.composefonts

import android.graphics.Typeface
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.annotation.FontRes
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.font.AndroidFont.TypefaceLoader
import compose_fonts.lib.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.ResourceItem
import java.io.File

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun loadFont(
    path: String,
    fontWeight: FontWeight
): State<FontFamily> {
    return remember {  mutableStateOf(FontFamily(Font(File(Res.getUri(path)), variationSettings = FontVariation.Settings(FontVariation.weight(fontWeight.weight))))) }
}



@Composable
actual fun loadFont(
    fontResource: FontResource,
    fontWeight: FontWeight
): State<FontFamily> {
    val bytes by loadFontBytes(fontResource)
    val dir = LocalContext.current.filesDir.resolve("font_cache.ttf")
    val written by produceState(false, key1 = bytes) {
        value = false
        bytes?.let {
            if (dir.exists()) dir.delete()
            dir.writeBytes(it)
            value = true
        }
    }
    return produceState<FontFamily>(FontFamily.Default, key1 = written, key2 = fontWeight) {
        if (written) {
            value = FontFamily(Font(dir, variationSettings = FontVariation.Settings(FontVariation.weight(fontWeight.weight))))
        }
    }
}