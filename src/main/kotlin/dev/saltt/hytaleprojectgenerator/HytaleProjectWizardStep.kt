package dev.saltt.hytaleprojectgenerator

import com.intellij.ide.wizard.AbstractNewProjectWizardStep
import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.validation.CHECK_NON_EMPTY
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.columns
import com.intellij.ui.dsl.builder.textValidation

class HytaleProjectWizardStep(
    parent: NewProjectWizardStep
) : AbstractNewProjectWizardStep(parent) {

    private val groupProperty =
        propertyGraph.property("com.example")

    private val descriptionProperty =
        propertyGraph.property("A Hytale Mod")

    private val authorProperty =
        propertyGraph.property("Author")

    private val mainClassProperty =
        propertyGraph.property("Main")

    private val languageProperty =
        propertyGraph.property(ProjectLanguage.JAVA)

    private val generateConfigProperty =
        propertyGraph.property(true)

    override fun setupUI(builder: Panel) {
        with(builder) {

            row("Group:") {
                textField()
                    .bindText(groupProperty)
                    .columns(30)
            }

            row("Description:") {
                textField()
                    .bindText(descriptionProperty)
                    .columns(30)
            }

            row("Author:") {
                textField()
                    .bindText(authorProperty)
                    .columns(30)
            }

            row("Main class:") {
                textField()
                    .bindText(mainClassProperty)
                    .columns(30)
                    .textValidation(CHECK_NON_EMPTY)
                    .validationOnInput {
                        val value = it.text.trim()

                        when {
                            value.isEmpty() ->
                                null

                            !value.first().isJavaIdentifierStart() ->
                                error("Class name must start with a letter or underscore")

                            !value.all { c -> c.isJavaIdentifierPart() } ->
                                error("Class name may only contain letters, digits and underscores")

                            else ->
                                null
                        }
                    }
            }

            row("Language:") {
                segmentedButton(ProjectLanguage.entries.toList()) {
                    text = it.displayName
                }.bind(languageProperty)
            }

            row("Server config:") {
                segmentedButton(listOf(true, false)) {
                    text = if (it) "Yes" else "No"
                }.bind(generateConfigProperty)
            }
        }
    }

    override fun setupProject(project: Project) {
        val data = HytaleProjectData(
            projectName = context.projectName,
            group = groupProperty.get(),
            description = descriptionProperty.get(),
            authorName = authorProperty.get(),
            mainClassName = mainClassProperty.get().trim(),
            language = languageProperty.get(),
            generateConfig = generateConfigProperty.get()
        )

        HytaleProjectGenerator.generate(
            projectDirectory = context.projectFileDirectory,
            data = data
        )
    }
}