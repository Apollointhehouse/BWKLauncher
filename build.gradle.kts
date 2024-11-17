@file:Suppress("PropertyName")

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	kotlin("jvm")
	kotlin("plugin.serialization") version "2.0.20"
	id("org.jetbrains.compose")
	id("org.jetbrains.kotlin.plugin.compose")
}

group = "me.apollointhehouse"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
	maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	maven("https://packages.jetbrains.team/maven/p/kpm/public/")
	google()
}

val ktor_version: String by project
val voyager_version: String by project

dependencies {

	// Network
	implementation("io.ktor:ktor-client-core:$ktor_version")
	implementation("io.ktor:ktor-client-cio:$ktor_version")
	implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
	implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

	// UI
	implementation(compose.desktop.currentOs)
	implementation(compose.components.resources)

	// Navigation
	implementation("cafe.adriel.voyager:voyager-navigator:$voyager_version")
	implementation("cafe.adriel.voyager:voyager-transitions:$voyager_version")

	// MC Auth
	implementation("net.raphimc:MinecraftAuth:4.1.1")

}

compose.desktop {
	application {
		mainClass = "MainKt"

		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "BWKLauncher"
			packageVersion = "1.0.0"
		}
	}
}
