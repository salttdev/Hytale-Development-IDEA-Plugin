package dev.saltt.hytaleprojectgenerator

import org.jetbrains.annotations.SystemDependent
import java.net.JarURLConnection
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.jar.JarFile

object HytaleProjectGenerator {

    private val executableFiles = setOf("gradlew")

    fun generate(
        projectDirectory: @SystemDependent String,
        data: HytaleProjectData
    ) {
        val replacements = mapOf(
            "{{PROJECT_NAME}}" to data.projectName,
            "{{GROUP}}" to data.group,
            "{{DESCRIPTION}}" to data.description,
            "{{AUTHOR_NAME}}" to data.authorName,
            "{{PACKAGE_NAME}}" to data.packageName,
            "{{PACKAGE_PATH}}" to data.packagePath,
            "{{MAIN_CLASS_NAME}}" to data.mainClassName
        )

        /*
         * Always copy the base template first.
         */
        val basicTemplate = when (data.language) {
            ProjectLanguage.JAVA ->
                "hytale/basic-java"

            ProjectLanguage.KOTLIN ->
                "hytale/basic-kotlin"
        }

        copyTemplate(
            resourcePath = basicTemplate,
            destination = projectDirectory,
            replacements = replacements
        )

        /*
         * If ServerConfig was requested, overlay the
         * optional template on top of the generated project.
         *
         * This replaces Main and adds ServerConfig.
         */
        if (data.generateConfig) {

            val optionalTemplate = when (data.language) {
                ProjectLanguage.JAVA ->
                    "hytale/optional/java"

                ProjectLanguage.KOTLIN ->
                    "hytale/optional/kotlin"
            }

            copyTemplate(
                resourcePath = optionalTemplate,
                destination = projectDirectory,
                replacements = replacements
            )
        }
    }

    private fun copyTemplate(
        resourcePath: String,
        destination: @SystemDependent String,
        replacements: Map<String, String>
    ) {
        val classLoader =
            HytaleProjectGenerator::class.java.classLoader

        val resource =
            classLoader.getResource(resourcePath)
                ?: error("Template not found: $resourcePath")

        when (resource.protocol) {

            "file" -> {
                val source = Path.of(resource.toURI())

                copyDirectory(
                    source = source,
                    destination = Path.of(destination),
                    replacements = replacements
                )
            }

            "jar" -> {
                val connection =
                    resource.openConnection() as JarURLConnection

                copyFromJar(
                    jar = connection.jarFile,
                    resourcePath = resourcePath,
                    destination = Path.of(destination),
                    replacements = replacements
                )
            }

            else -> {
                error(
                    "Unsupported template resource protocol: " +
                            resource.protocol
                )
            }
        }
    }

    private fun copyDirectory(
        source: Path,
        destination: Path,
        replacements: Map<String, String>
    ) {
        Files.walk(source).use { stream ->

            stream.forEach { sourcePath ->

                val relativePath =
                    source.relativize(sourcePath)

                val replacedPath =
                    replacePlaceholders(
                        relativePath.toString(),
                        replacements
                    )

                val targetPath =
                    destination.resolve(replacedPath)

                if (Files.isDirectory(sourcePath)) {
                    Files.createDirectories(targetPath)
                    return@forEach
                }

                Files.createDirectories(targetPath.parent)

                copyFile(
                    source = sourcePath,
                    destination = targetPath,
                    replacements = replacements
                )
            }
        }
    }

    private fun copyFromJar(
        jar: JarFile,
        resourcePath: String,
        destination: Path,
        replacements: Map<String, String>
    ) {
        val prefix =
            if (resourcePath.endsWith("/")) {
                resourcePath
            } else {
                "$resourcePath/"
            }

        jar.entries()
            .asSequence()
            .filter { it.name.startsWith(prefix) }
            .forEach { entry ->

                val relativePath =
                    entry.name.removePrefix(prefix)

                if (relativePath.isEmpty()) {
                    return@forEach
                }

                val replacedPath =
                    replacePlaceholders(
                        relativePath,
                        replacements
                    )

                val targetPath =
                    destination.resolve(replacedPath)

                if (entry.isDirectory) {
                    Files.createDirectories(targetPath)
                    return@forEach
                }

                Files.createDirectories(targetPath.parent)

                jar.getInputStream(entry).use { input ->
                    Files.copy(
                        input,
                        targetPath,
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }

                replaceFileContents(
                    targetPath,
                    replacements
                )

                applyExecutableBit(targetPath)
            }
    }

    private fun copyFile(
        source: Path,
        destination: Path,
        replacements: Map<String, String>
    ) {
        Files.copy(
            source,
            destination,
            StandardCopyOption.REPLACE_EXISTING
        )

        replaceFileContents(
            destination,
            replacements
        )

        applyExecutableBit(destination)
    }

    /*
     * Jar entries carry no POSIX permissions, and
     * Files.copy does not preserve them from disk either.
     *
     * Without this, gradlew is generated non-executable
     * on macOS and Linux.
     *
     * The second argument to setExecutable is ownerOnly,
     * so passing false yields 755 rather than 744.
     *
     * Windows filesystems may reject the call entirely,
     * which is harmless there since gradlew.bat is used.
     */
    private fun applyExecutableBit(file: Path) {
        val fileName =
            file.fileName.toString()

        if (fileName !in executableFiles) {
            return
        }

        runCatching {
            file.toFile().setExecutable(true, false)
        }
    }

    private fun replaceFileContents(
        file: Path,
        replacements: Map<String, String>
    ) {
        if (!isTextFile(file)) {
            return
        }

        val content =
            Files.readString(file)

        val replaced =
            replacePlaceholders(
                content,
                replacements
            )

        if (content != replaced) {
            Files.writeString(
                file,
                replaced
            )
        }
    }

    private fun replacePlaceholders(
        value: String,
        replacements: Map<String, String>
    ): String {
        var result = value

        replacements.forEach { (placeholder, replacement) ->
            result = result.replace(
                placeholder,
                replacement
            )
        }

        return result
    }

    private fun isTextFile(file: Path): Boolean {
        val fileName =
            file.fileName
                .toString()
                .lowercase()

        return fileName.endsWith(".java") ||
                fileName.endsWith(".kt") ||
                fileName.endsWith(".kts") ||
                fileName.endsWith(".json") ||
                fileName.endsWith(".md") ||
                fileName.endsWith(".properties") ||
                fileName.endsWith(".xml") ||
                fileName.endsWith(".txt") ||
                fileName.endsWith(".yml") ||
                fileName.endsWith(".yaml") ||
                fileName.endsWith(".gradle")
    }
}