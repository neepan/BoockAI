import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("boockai.android.library")
                apply("boockai.android.hilt")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                buildFeatures {
                    compose = true
                }
            }

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:domain"))

                add("implementation", "androidx.hilt:hilt-navigation-compose:1.3.0")
                add("implementation", "androidx.lifecycle:lifecycle-runtime-compose:2.10.0")
                add("implementation", "androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
            }
        }
    }
}
