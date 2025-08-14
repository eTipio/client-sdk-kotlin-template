import org.jmailen.gradle.kotlinter.tasks.FormatTask
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kotlinter)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":sdk-core"))
    implementation(libs.dotenv.java)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.kotlinx)
    implementation(libs.bundles.logging)
    implementation(libs.bundles.koin)
}

kotlinter {
    ignoreLintFailures = false
    reporters = arrayOf("checkstyle", "plain")
}

tasks.withType<LintTask> {
    source = source.minus(fileTree("build/generated")).asFileTree
}

tasks.withType<FormatTask> {
    source = source.minus(fileTree("build/generated")).asFileTree
}