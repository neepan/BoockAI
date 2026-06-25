plugins {
    id("boockai.android.feature")
}

android {
    namespace = "com.neepan.boockai.feature.library"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.designsystem)

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.turbine)
    testImplementation(libs.coroutines.test)
}