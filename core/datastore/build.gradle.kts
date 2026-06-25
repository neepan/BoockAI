plugins {
    id("boockai.android.library")
    id("boockai.android.hilt")
}

android {
    namespace = "com.neepan.boockai.core.datastore"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(libs.androidx.datastore.preferences)
}