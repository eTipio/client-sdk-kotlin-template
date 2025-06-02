plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "client-sdk-kotlin-template"
include("sdk-core", "sdk-api", "examples:basic-example")
