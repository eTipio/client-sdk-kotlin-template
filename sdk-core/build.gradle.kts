plugins {
    `maven-publish`
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
}

group = "io.etip"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.kotlinx)
    implementation(libs.bundles.logging)

    testImplementation(libs.bundles.test)
    testImplementation(kotlin("test"))

    implementation(
        fileTree("libs") {
            include("*.jar")
        }
    )
}
