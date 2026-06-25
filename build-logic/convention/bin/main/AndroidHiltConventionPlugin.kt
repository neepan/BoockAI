import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            pluginManager.apply("com.google.dagger.hilt.android")

            val libs= extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
//                add("implementation", "com.google.dagger:hilt-android:2.59.2")
//                add("ksp", "com.googlpendenciese.dagger:hilt-compiler:2.59.2")

                add("implementation", libs.findLibrary("hilt-android").get())
                add("ksp", libs.findLibrary("hilt-compiler").get())
                add("implementation","androidx.hilt:hilt-navigation-compose:1.3.0")
                add("implementation","androidx.hilt:hilt-work:1.3.0")
                add("ksp","androidx.hilt:hilt-compiler:1.3.0")
                add("implementation","androidx.work:work-runtime-ktx:2.10.0")
            }
        }
    }
}
