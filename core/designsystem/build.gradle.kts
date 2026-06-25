plugins {
    id("boockai.android.library")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.neepan.boockai.core.designsystem"

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)
    
    api(libs.coil.compose)
    api(libs.coil.network.okhttp)
    api(libs.androidx.core.ktx)
}