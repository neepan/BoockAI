plugins {
    `kotlin-dsl`
}

dependencies {


    compileOnly("com.android.tools.build:gradle:${libs.versions.agp.get()}")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")


}
gradlePlugin{
    plugins{
        register("androidFeature") {
            id = "boockai.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "boockai.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidLibrary") {
            id = "boockai.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}