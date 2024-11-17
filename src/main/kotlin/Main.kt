import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.App
import java.io.File

val userDir = System.getProperty("user.dir") ?: error("user.dir not found")
val launcherPath = "$userDir/.bwklauncher".also { File(it).mkdirs() }

fun main() = application {
	Window(onCloseRequest = ::exitApplication, title = "BWKLauncher") {
		App()
	}
}
