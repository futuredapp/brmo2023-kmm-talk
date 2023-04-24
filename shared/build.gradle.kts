plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            export("com.arkivanov.decompose:decompose:2.0.0-alpha-02")
            export("com.arkivanov.essenty:lifecycle:1.1.0")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.arkivanov.decompose:decompose:2.0.0-alpha-02")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                api("com.arkivanov.decompose:decompose:2.0.0-alpha-02")
                api("com.arkivanov.essenty:lifecycle:1.1.0")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "app.futured.brmo23"
    compileSdk = 33
    defaultConfig {
        minSdk = 28
        targetSdk = 33
    }
}
