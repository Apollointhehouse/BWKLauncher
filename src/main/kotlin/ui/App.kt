package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import data.Auth
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import net.raphimc.minecraftauth.step.java.session.StepFullJavaSession
import ui.screen.HomeScreen
import ui.screen.LoginScreen
import androidx.compose.material.*

@Composable
fun App() = MaterialTheme {
	var session: StepFullJavaSession.FullJavaSession? by remember { mutableStateOf(Auth.getLoadedProfile()) }

	Navigator(session?.let { HomeScreen(it) } ?: LoginScreen { session = it }) { navigator ->
		SlideTransition(navigator)
	}
}