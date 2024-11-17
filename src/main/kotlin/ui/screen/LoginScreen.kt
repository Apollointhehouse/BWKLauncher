package ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import data.Auth.loginWithCode
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.raphimc.minecraftauth.step.java.session.StepFullJavaSession
import utils.openInBrowser
import java.net.URI

data class LoginScreen(val onAuthSession: (StepFullJavaSession.FullJavaSession) -> Unit) : Screen {
	@Composable
	override fun Content() {
		val scope = rememberCoroutineScope()
		val navigator = LocalNavigator.currentOrThrow
		var code: String? by remember { mutableStateOf(null) }

		Column(
			modifier = Modifier.fillMaxSize(),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = "Authenticate with MS",
			)

			Spacer(
				modifier = Modifier.height(10.dp)
			)

			IconButton(onClick = {
				println("Button clicked")
				scope.launch(Dispatchers.IO) {
					val session = loginWithCode { msaDeviceCode ->
						val uri = URI(msaDeviceCode.verificationUri)
						uri.openInBrowser()
						code = msaDeviceCode.userCode
					}
					onAuthSession(session)
					navigator.push(HomeScreen(session))
				}
			}) {
				Text("Login")
			}

			code?.let { Text("Enter $it to auth") }
		}
	}
}