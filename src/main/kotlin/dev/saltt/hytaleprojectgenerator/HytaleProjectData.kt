package dev.saltt.hytaleprojectgenerator

data class HytaleProjectData(
    val projectName: String,
    val group: String,
    val description: String,
    val authorName: String,
    val language: ProjectLanguage,
    val generateConfig: Boolean
) {
    val packageName: String
        get() = "$group.${projectName.lowercase()}"

    val packagePath: String
        get() = packageName.replace('.', '/')

    val mainClassName: String
        get() = projectName
            .replace(Regex("[^A-Za-z0-9]"), " ")
            .split(" ")
            .filter { it.isNotBlank() }
            .joinToString("") {
                it.replaceFirstChar(Char::uppercase)
            }
}