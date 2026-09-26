package com.example.groceryapp.utils

import android.graphics.Color.alpha
import android.graphics.Color.blue
import android.graphics.Color.green
import android.graphics.Color.red
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.imageResource
import androidx.core.graphics.ColorUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val colorCache = mutableMapOf<Int, Color>()

@Composable
fun rememberAverageColor(
    @DrawableRes imageId: Int,
    fallback: Color = Color(0xFFF5F5F5)
): State<Color> {

    val initColor = colorCache[imageId] ?: fallback
    val colorState = remember(imageId) { mutableStateOf(initColor) }
    val imageBitmap = ImageBitmap.imageResource(imageId)

    LaunchedEffect(imageId) {
        if (colorCache.contains(imageId))return@LaunchedEffect

        val computed = withContext(Dispatchers.Default){
            computeAverageColor(imageBitmap, fallback)
        }
        colorCache[imageId] = computed
        colorState.value = computed
    }
    return colorState
}

private fun computeAverageColor(
    imageBitmap: ImageBitmap,
    fallback: Color
): Color{
    val bitmap = imageBitmap.asAndroidBitmap()

    val wight = bitmap.width
    val height = bitmap.height
    if (wight == 0 || height == 0) return fallback

    val pixels = IntArray(wight * height)
    bitmap.getPixels(
        pixels,
        0,
        wight,
        0,0,
        wight,
        height
    )

    var rSum = 0L
    var gSum = 0L
    var bSum = 0L
    var count = 0L

    for (pixel in pixels) {
        // пропуск почти прозрачных пикселей
        val alpha = alpha(pixel)
        if (alpha < 128) continue

        rSum += red(pixel)
        gSum += green(pixel)
        bSum += blue(pixel)
        count++
    }

    if (count == 0L)return fallback

    val r = (rSum/ count).toInt()
    val g = (gSum/count).toInt()
    val b = (bSum/count).toInt()

    val average = Color(r,g,b)

    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(average.toArgb(), hsl)
    hsl[1] = (hsl[1] * 0.9f).coerceIn(0f,1f)
    hsl[2] = (hsl[2] * 0.3f + 0.70f).coerceIn(0f, 1f)

    return Color(ColorUtils.HSLToColor(hsl))
}