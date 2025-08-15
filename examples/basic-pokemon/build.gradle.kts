plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":sdk-core"))
    implementation(project(":sdk-api"))
    implementation(libs.dotenv.java)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.kotlinx)
    implementation(libs.bundles.koin)
    implementation("io.insert-koin:koin-core:3.5.3")
}

application {
    mainClass.set("io.etip.sdk.examples.pokemon.AppKt")
}
