import me.omico.gradle.initialization.includeAllSubprojectModules
import me.omico.gradm.addDeclaredRepositories

addDeclaredRepositories()

plugins {
    id("pizza.gradm")
    id("pizza.gradle-enterprise")
}

includeBuild("build-logic/project")

includeAllSubprojectModules("pizza")
