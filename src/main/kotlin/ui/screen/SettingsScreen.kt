package ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import net.raphimc.minecraftauth.step.java.session.StepFullJavaSession

data class SettingsScreen(val session: StepFullJavaSession.FullJavaSession) : Screen {
	@Composable
	override fun Content() {
		val navigator = LocalNavigator.currentOrThrow

		Column(
			modifier = Modifier.fillMaxSize(),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Button(onClick = {
				navigator.pop()
			}) {
				Text("Home")
			}
		}

	}
}