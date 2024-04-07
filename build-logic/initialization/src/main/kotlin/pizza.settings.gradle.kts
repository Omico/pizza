import me.omico.gradle.initialization.includeAllSubprojectModules
import me.omico.gradm.addDeclaredRepositories

addDeclaredRepositories()

plugins {
    id("pizza.develocity")
    id("pizza.gradm")
}

includeBuild("build-logic/project")

includeAllSubprojectModules("pizza")
