pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LWBDPOC"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":lint")

include(":core:base")
include(":core:common")
include(":core:data")
include(":core:data-test")
include(":core:designsystem")
include(":core:model")
include(":core:network")
include(":core:testing")
include(":core:ui")

include(":feature:home")
