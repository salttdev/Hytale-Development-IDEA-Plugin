import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.2.0"
    id("org.jetbrains.intellij.platform") version "2.18.1"
}

group = "dev.saltt"
version = "1.1.0"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}


dependencies {
    intellijPlatform {
        intellijIdea("2026.1.5")

        testFramework(TestFrameworkType.Platform)

        bundledPlugin("com.intellij.java")
    }
}

intellijPlatform {
    buildSearchableOptions = false

    pluginConfiguration {
        ideaVersion {
            sinceBuild = "252"
            untilBuild = provider { null }
        }
    }

    signing {
        certificateChainFile = file(".keys/chain.crt")
        privateKeyFile = file(".keys/private.pem")
        password = providers.gradleProperty("signingPassword")
    }

    publishing {
        token = providers.gradleProperty("intellijPlatformPublishingToken")
    }
}

sourceSets {
    main {
        resources {
            srcDir("templates")
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}