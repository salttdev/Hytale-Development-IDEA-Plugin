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

## License

See [LICENSE](./LICENSE).

---

Not affiliated with, endorsed by, or sponsored by Hypixel Studios.