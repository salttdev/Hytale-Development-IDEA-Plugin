package dev.saltt.hytaleprojectgenerator

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.ide.wizard.GeneratorNewProjectWizard
import com.intellij.ide.wizard.NewProjectWizardBaseStep
import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.ide.wizard.RootNewProjectWizardStep
import com.intellij.icons.AllIcons
import com.intellij.ide.wizard.NewProjectWizardChainStep.Companion.nextStep
import javax.swing.Icon

class HytaleProjectWizard : GeneratorNewProjectWizard {

    override val id: String
        get() = "hytale"

    override val name: String
        get() = "Hytale"

    override val icon: Icon
        get() = AllIcons.Nodes.Module

    override val ordinal: Int
        get() = 100

    override fun createStep(
        context: WizardContext
    ): NewProjectWizardStep {
        return RootNewProjectWizardStep(context)
            .nextStep(::NewProjectWizardBaseStep)
            .nextStep(::HytaleProjectWizardStep)
    }
}