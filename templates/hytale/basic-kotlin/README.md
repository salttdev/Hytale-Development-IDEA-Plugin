# Hytale Mod Template

A basic Java template for creating Hytale server mods.

While the template works out of the box, it is recommended to change the `ServerVersion` in your `manifest.json` from `"*"` to your target Hytale server version.

## Gradle Tasks

Use the template's Gradle tasks to build and run your mod.

- `clean` — Clears the build directory.
- `cleanShadowJar` — Cleans the build directory and builds a Shadow JAR.
- `shadowJar` — Builds a Shadow JAR.
- `devServer` — Runs a test Hytale server from your IDE.

## Shadow JAR

This project builds Shadow JARs by default. If no external dependencies are declared, this effectively behaves like a normal build.

If you do not want an implementation dependency bundled in your JAR, add an appropriate `exclude` rule to the `shadowJar` block.

## Dev Server

You can edit and add mods to the development server environment by modifying the `devserver` directory.
The devServer also builds the mod to include it in its runtime.

## Jar output

The built shadowJar is outputted to build/libs/