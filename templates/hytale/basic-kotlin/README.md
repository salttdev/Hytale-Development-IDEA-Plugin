# {{PROJECT_NAME}}

{{DESCRIPTION}}

A Hytale server mod built with Kotlin.

> [!NOTE]
> This project requires **JDK 25**.

## Gradle Tasks

| Task             | Description                                     |
|------------------|-------------------------------------------------|
| `shadowJar`      | Builds a Shadow JAR to `build/libs/`            |
| `cleanShadowJar` | Cleans the build directory, then builds the JAR |
| `devServer`      | Runs a test Hytale server from your IDE         |
| `clean`          | Clears the build directory                      |

Run them from the Gradle tool window under **Template**, or from the terminal:

```bash
./gradlew shadowJar
./gradlew devServer
```

> [!TIP]
> You can change the name of the task group in `build.settings.kts` under `gradleGroup`

> [!TIP]
> It is recommended to change `ServerVersion` in your `manifest.json` from `"*"` to your target Hytale server version.

## Shadow JAR

This project builds Shadow JARs by default. With no external dependencies declared, this behaves like a normal build.

To keep a dependency out of your JAR, add an `exclude` rule to the `shadowJar` block:

```kotlin
tasks.shadowJar {
    dependencies {
        exclude(dependency("com.example:library:.*"))
    }
}
```

Output lands in `build/libs/`.

> [!IMPORTANT]
> The Kotlin standard library is bundled into your Shadow JAR by default.

## Dev Server

Edit the `devserver` directory to configure the development server or add other mods to it.

`devServer` builds your mod and includes it in the server's runtime, so there's no separate build step.

---

Generated with [Hytale Project Setup](https://plugins.jetbrains.com/plugin/33516).