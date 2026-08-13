plugins {
    java
    id("com.gradleup.shadow") version "8.3.0"
}

val javaVersion = 25

group = "{{GROUP}}"
version = "1.0.0"

repositories {
    // External repos
}

dependencies {

    //Extenal dependancies

    compileOnly(fileTree("libs") {
        include("*.jar")
    })
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
    }
    withSourcesJar()
}


tasks.shadowJar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    mergeServiceFiles()

    dependencies {
        exclude(dependency("com.hypixel.hytale:Server:.*"))
        exclude(dependency("dev.scaffoldit:.*:.*"))
    }
}

// gradle tasks

val gradleGroup = "Template"

tasks.named("clean") {
    group = gradleGroup
}

tasks.named("devServer") {
    group = gradleGroup
}

// The combined one needs its own name
tasks.register("cleanShadowJar") {
    group = gradleGroup
    description = "Clean, then build the shadow jar."
    dependsOn("clean", "shadowJar")
}

tasks.named("shadowJar") {
    group = gradleGroup
    mustRunAfter("clean")
}