# Hytale Project Setup

[![Version](https://img.shields.io/jetbrains/plugin/v/33516.svg)](https://plugins.jetbrains.com/plugin/33516)

Ready-to-run Hytale mod projects, straight from the IntelliJ IDEA project wizard.

- **No local Hytale install** required
- **Shadow JAR** packaging preconfigured
- **Dev server** built in, runs from the IDE
- **Clean Gradle setup** — no commands needed
- **Java and Kotlin**, with optional template config

## Usage

**File** → **New** → **Project** → **Hytale**

Fill in your group, description and author, pick Java or Kotlin, and click **Create**.

> [!NOTE]
> Hytale projects require **JDK 25 or newer**.

## What you get

```
.
├── devserver/              In IDE dev server
│   ├── config.json         
│   ├── mods/               Additional mods for dev server
│   └── universe/           
├── gradle
│   └── wrapper/            
├── src
│   └── main
│       ├── java/           (Or kotlin)
│           ├── Main.java
│           └── ServerConfig.java (Optional)
│       └── resources/
│           └── manifest.json   
├── build.gradle.kts       
├── gradlew                 
├── gradlew.bat             
├── README.md               
└── settings.gradle.kts     
```

A main class and `manifest.json` are generated from your wizard input. Choosing **Server config** also scaffolds a
`ServerConfig` class alongside it.

Easy to use gradle tasks

| Task             | Description                                        |
|------------------|----------------------------------------------------|
| `shadowJar`      | Builds a Shadow JAR to `build/libs/`               |
| `cleanShadowJar` | Cleans the build directory, then builds the JAR    |
| `devServer`      | Runs a test Hytale server from your IDE            |
| `clean`          | Clears the build directory                         |

## Building the plugin

```bash
./gradlew runIde        # Sandbox IDE with the plugin loaded
./gradlew buildPlugin   # Distributable zip in build/distributions/
```

Templates live in `templates/hytale/` with `{{PLACEHOLDER}}` tokens as placeholders for
path names. The base template is copied first, then the optional overlay on top.

---

## Project README (From a kotlin project)

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
Environment variables passed into the dev server are configurable in `build.gradle.kts` under `devServerEnv`.
> [!IMPORTANT]
> It's not recommended to add any key based env vars here.

`devServer` builds your mod and includes it in the server's runtime, so there's no separate build step.

---

Generated with [Hytale Project Setup](https://plugins.jetbrains.com/plugin/33516).

## License

See [LICENSE](./LICENSE).

---



Not affiliated with, endorsed by, or sponsored by Hypixel Studios.
