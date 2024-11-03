package net

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

object APIClient {
	private val client = HttpClient(CIO) {
		install(ContentNegotiation) {
			json()
		}
	}
}