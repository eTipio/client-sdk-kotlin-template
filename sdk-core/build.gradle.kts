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

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["kotlin"])

            groupId = "io.etip"
            artifactId = "sdk-template-kotlin"
            version = System.getenv("GITHUB_REF")?.substringAfterLast("/") ?: "local"
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/eTipio/sdk-template-kotlin")
            credentials {
                username = project.findProperty("release.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("release.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
}
