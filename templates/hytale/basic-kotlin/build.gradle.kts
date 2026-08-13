plugins {
    kotlin("jvm")
    id("com.gradleup.shadow") version "9.6.1"
}

val javaVersion = 25

group = "com.example"
version = "1.0.0"

repositories {
    // External repos
}

dependencies {

    // External dependencies

    compileOnly(fileTree("libs") {
        include("*.jar")
    })
}

kotlin {
    jvmToolchain(javaVersion)
}


tasks.shadowJar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    mergeServiceFiles()

    dependencies {
        exclude(dependency("com.hypixel.hytale:Server:.*"))
        exclude(dependency("dev.scaffoldit:.*:.*"))
    }
}

// Gradle tasks

val gradleGroup = "Template"

tasks.named("clean") {
    group = gradleGroup
}

tasks.matching { it.name == "devServer" }.configureEach {
    group = gradleGroup
}

tasks.register("cleanShadowJar") {
    group = gradleGroup
    description = "Clean, then build the shadow jar."
    dependsOn("clean", "shadowJar")
}

tasks.named("shadowJar") {
    group = gradleGroup
    mustRunAfter("clean")
}