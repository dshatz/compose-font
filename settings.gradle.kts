pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        kotlin("multiplatform").version(extra["kotlin.version"] as String) apply false
        id("org.jetbrains.compose").version(extra["compose.version"] as String) apply false
    }
}

rootProject.name = "compose-fonts"
include(":lib")
include(":sample")
