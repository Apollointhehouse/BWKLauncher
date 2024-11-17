package data

import kotlinx.serialization.Serializable

@Serializable
data class Config(
	val name: String,
	val theme: String,
	val version: String,
)