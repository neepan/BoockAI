plugins {
    id("boockai.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.neepan.boockai.core.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}