plugins {
    id("com.android.application")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

kotlin {
    androidTarget()
    jvm()
    jvmToolchain(17)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":lib"))
                implementation(compose.desktop.common)
                implementation(compose.material)
                implementation(compose.components.resources)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.appcompat:appcompat:1.7.0")
                implementation("androidx.activity:activity-compose:1.8.0")
            }
        }
    }
}

android {
    namespace = "com.dshatz.compose_fonts"
    defaultConfig {
        minSdk = 21
        compileSdk = 34
        targetSdk = 34
    }
}