plugins {
    id("boockai.android.feature")
}

android {
    namespace = "com.neepan.boockai.feature.reader"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.datastore)
    implementation(projects.core.designsystem)
}