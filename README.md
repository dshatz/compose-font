# Load TTF variable font from Compose Multiplatform

Make loading differently weighted fonts from one .ttf file easier. Hopefully this will be supported in Compose Multiplatform natively sometime in the future.

1. Put a single variable .ttf font file in `composeResources/font`.
2. `val fontFamily by loadFont(Res.font.your_font, FontWeight(500))`
3. `Text("The quick brown fox jumps over the lazy dog", fontFamily = fontFamily)`

## Supported platforms
 - JVM
 - Android

## Implementation notes
### JVM
On JVM, jetbrains skia api is used.
### Android
On Android, the font will be written to app `filesDir` once and then loaded into memory and copied with `variationSettings` whenever the `fontWeight` changes.
Unfortunately there is no way to directly load a font on android from composeResources/ folder while also applying `variationSettings`.

## Contributing
Merge requests welcome, especially for adding iOS support.
