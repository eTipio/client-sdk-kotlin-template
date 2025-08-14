plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "sdk-template-kotlin"
include("sdk-core", "sdk-api", "examples:basic-pokemon")
