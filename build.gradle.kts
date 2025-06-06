plugins {
    application
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.serialization") version "2.1.20"
    id("io.ktor.plugin") version "3.1.1"
    id("me.qoomon.git-versioning") version "6.4.4"
    id("io.sentry.jvm.gradle") version "5.3.0"
}

val ktorVersion: String by project
val kotlinVersion: String by project

group = "org.mapsnotincluded"

gitVersioning.apply {

    refs {
        /* Main branch contains the current dev version */
        branch("main") {
            version = "\${commit.short}"
        }
    }

    /* Fallback if branch was not found (for feature branches) */
    rev {
        version = "\${commit.short}"
    }
}

application {

    mainClass.set("ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

sourceSets {
    main {
        kotlin {
            srcDir(layout.buildDirectory.dir("generated/source"))
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {

    /*
     * Ktor server
     */

    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")

    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-compression:$ktorVersion")
    implementation("io.ktor:ktor-server-cors:$ktorVersion")

    implementation("io.ktor:ktor-server-auth:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")
    implementation("io.ktor:ktor-server-html-builder:$ktorVersion")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-cbor:$ktorVersion")

    /*
     * Ktor client
     */

    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

    /*
     * MongoDB handling
     */
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.2.0")
    implementation("org.mongodb:bson-kotlinx:5.2.0")

    /*
     * Biome path optimization
     */
    implementation("com.menecats:polybool-java:1.0.1")

    /*
     * JWT handling
     */
    implementation("com.auth0:java-jwt:4.5.0")

    /*
     * Unit tests
     */
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

// region Version
project.afterEvaluate {

    logger.lifecycle("Generate Version.kt")

    val outputDir = layout.buildDirectory.file("generated/source/").get().asFile

    outputDir.mkdirs()

    val file = File(outputDir.absolutePath, "Version.kt")

    file.printWriter().use { writer ->

        writer.println("const val VERSION: String = \"$version\"")

        writer.flush()
    }
}
// endregion
