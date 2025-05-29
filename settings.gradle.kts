plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "client-sdk-kotlin-template"
include("sdk-core", "sdk-api", "examples:basic-example")
