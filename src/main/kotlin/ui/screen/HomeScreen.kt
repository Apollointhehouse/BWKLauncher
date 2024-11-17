package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.*
import me.apollointhehouse.bwklauncher.generated.resources.*
import net.raphimc.minecraftauth.step.java.session.StepFullJavaSession
import utils.toTileable

data class HomeScreen(val session: StepFullJavaSession.FullJavaSession) : Screen {
	@Composable
	override fun Content() {
		Column(
			modifier = Modifier.fillMaxSize(),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Navigation()

			Instances()
		}
	}

	@Composable
	private fun ColumnScope.Instances() {
		Row(
			modifier = Modifier
				.weight(0.5F)
				.fillMaxWidth()
				.align(Alignment.CenterHorizontally)
				.background(Res.drawable.stone.toTileable()),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.CenterVertically
		) {
			Card(
				modifier = Modifier.size(256.dp)
			) {
				Text("Vanilla", modifier = Modifier.padding(16.dp))
			}

			Card(
				modifier = Modifier.size(256.dp)
			) {
				Text("Vanilla2", modifier = Modifier.padding(16.dp))
			}
		}
	}

	@Composable
	private fun ColumnScope.Navigation() {
		val navigator = LocalNavigator.currentOrThrow

		Row(
			modifier = Modifier
				.height(80.dp)
				.fillMaxWidth()
				.align(Alignment.CenterHorizontally)
				.background(Res.drawable.dirt.toTileable()),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			Button(
				modifier = Modifier.height(35.dp),
				onClick = { navigator.push(SettingsScreen(session)) }
			) {
				Text("Settings")
			}

			Spacer(modifier = Modifier.width(25.dp))

			Button(
				modifier = Modifier
					.height(35.dp),
				onClick = { navigator.push(BlogScreen(session)) }
			) {
				Text("Blog")
			}
		}
	}
}