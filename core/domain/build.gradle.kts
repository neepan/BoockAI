plugins {
    id("boockai.android.library")
}

android {
    namespace = "com.neepan.boockai.core.domain"
}

dependencies {
    api(projects.core.model)
    implementation(libs.kotlinx.coroutines.core)
}