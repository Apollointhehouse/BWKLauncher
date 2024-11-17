package data

import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import launcherPath
import net.raphimc.minecraftauth.MinecraftAuth
import net.raphimc.minecraftauth.step.java.session.StepFullJavaSession
import net.raphimc.minecraftauth.step.msa.StepMsaDeviceCode.MsaDeviceCode
import net.raphimc.minecraftauth.step.msa.StepMsaDeviceCode.MsaDeviceCodeCallback
import java.io.File
import java.io.FileWriter
import java.util.function.Consumer


object Auth {
	private val profileFile = File("$launcherPath/profile.json").also { it.createNewFile() }

	suspend fun loginWithCode(msaCallback: Consumer<MsaDeviceCode>): StepFullJavaSession.FullJavaSession {
		val httpClient = MinecraftAuth.createHttpClient()
		val javaSession = MinecraftAuth.JAVA_DEVICE_CODE_LOGIN.getFromInput(
			httpClient,
			MsaDeviceCodeCallback(msaCallback)
		)

		val serializedSession = MinecraftAuth.JAVA_DEVICE_CODE_LOGIN.toJson(javaSession)

		withContext(Dispatchers.IO) {
			FileWriter("$launcherPath/profile.json").use { it.write(serializedSession.toString()) }
		}

		return javaSession
	}

	fun getLoadedProfile(): StepFullJavaSession.FullJavaSession? {
		val session = try {
			val text = profileFile.readText().takeIf { it.isNotEmpty() } ?: error("Profile file is empty")
			val json = JsonParser.parseString(text).asJsonObject ?: error("Profile file is empty")
			MinecraftAuth.JAVA_DEVICE_CODE_LOGIN.fromJson(json)
		} catch (e: Exception) {
			return null
		}

		println("Username: " + session.mcProfile.name)
		println("Access token: " + session.mcProfile.mcToken.accessToken)
//		println("Player certificates: " + session.playerCertificates)

		return session
	}
}