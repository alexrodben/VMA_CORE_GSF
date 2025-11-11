// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.library") || plugins.hasPlugin("maven-publish")) {
            extensions.findByType<PublishingExtension>()?.apply {
                publications {
                    create<MavenPublication>("release") {
                        groupId = "com.github.alexrodben"
                        artifactId = project.name // usa el nombre del módulo (api, core, etc.)
                        version = rootProject.version.toString()

                        afterEvaluate {
                            from(components.findByName("release"))
                        }
                    }
                }
            }
        }
    }
}
