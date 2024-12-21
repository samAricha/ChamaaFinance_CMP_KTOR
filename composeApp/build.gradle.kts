import org.gradle.kotlin.dsl.android
import org.gradle.kotlin.dsl.compose
import org.gradle.kotlin.dsl.implementation
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    alias(libs.plugins.ksp)
    alias(libs.plugins.room)

    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.room.runtime.android)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
//            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(projects.shared)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)

            implementation(libs.kotlinx.datetime)
            implementation(libs.kottie.animation)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.bundles.ktor)


        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "com.teka.chamaa_finance"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.teka.chamaa_finance"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }


//    ksp {
//        arg("room.schemaLocation", "$projectDir/schemas")
//    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.teka.chamaa_finance.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.teka.chamaa_finance"
            packageVersion = "1.0.0"
        }
    }
}


room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    ksp(libs.room.compiler)
    add("kspAndroid", libs.room.compiler)
    add("kspDesktop", libs.room.compiler)
}
