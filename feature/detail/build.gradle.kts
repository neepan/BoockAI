plugins {
    id("boockai.android.feature")
}

android {
    namespace = "com.neepan.boockai.feature.detail"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
}