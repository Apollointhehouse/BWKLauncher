package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.*
import org.jetbrains.compose.resources.*
import java.awt.Desktop
import java.net.URI

fun URI.openInBrowser() {
	val osName by lazy(LazyThreadSafetyMode.NONE) { System.getProperty("os.name").lowercase() }
	val desktop = Desktop.getDesktop()
	when {
		Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE) -> desktop.browse(this)
		"mac" in osName -> Runtime.getRuntime().exec("open $this")
		"nix" in osName || "nux" in osName -> Runtime.getRuntime().exec("xdg-open $this")
		else -> throw RuntimeException("cannot open $this")
	}
}

@Composable
fun DrawableResource.toTileable(): ShaderBrush {
	val img = imageResource(this)
	val brush = remember(img) { ShaderBrush(ImageShader(img, TileMode.Repeated, TileMode.Repeated)) }
	return brush
}