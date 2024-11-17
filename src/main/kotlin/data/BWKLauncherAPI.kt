package data

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object BWKLauncherAPI {
	val client = HttpClient(CIO) {
		install(ContentNegotiation) {
			json()
		}
	}

//	fun getBlogs() {
//		client.get("")
//
//	}


}