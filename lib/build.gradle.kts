import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.vanniktech.maven.publish") version "0.29.0"
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
                implementation(compose.desktop.common)
                implementation(compose.components.resources)
            }
        }
    }
}

android {
    namespace = "com.dshatz.compose_fonts"
    defaultConfig {
        minSdk = 21
        compileSdk = 34
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.S01, automaticRelease = true)
    signAllPublications()

    val version = System.getenv("PUBLISH_VERSION")
    coordinates("com.dshatz.compose-mpp", "compose-font", version)

    pom {
        name.set(project.name)
        description.set("Load variable fonts in Compose Multiplatform.")
        inceptionYear.set("2024")
        url.set("https://github.com/dshatz/compose-font/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("dshatz")
                name.set("Daniels Šatcs")
                url.set("https://github.com/dshatz/")
            }
        }
        scm {
            url.set("https://github.com/dshatz/compose-font/")
            connection.set("scm:git:git://github.com/dshatz/compose-font.git")
            developerConnection.set("scm:git:ssh://git@github.com/dshatz/compose-font.git")
        }
    }
}
