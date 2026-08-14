package dev.saltt.hytaleprojectgenerator

data class HytaleProjectData(
    val projectName: String,
    val group: String,
    val description: String,
    val authorName: String,
    val mainClassName: String,
    val language: ProjectLanguage,
    val generateConfig: Boolean
) {
    val packageName: String
        get() = "$group.${projectName.lowercase()}"

    val packagePath: String
        get() = packageName.replace('.', '/')
}