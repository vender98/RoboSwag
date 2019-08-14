plugins {
    id("com.android.library")
}

val versions: Map<String, *> by rootProject.extra
android {
    compileSdkVersion(versions["compileSdk"] as Int)

    defaultConfig {
        minSdkVersion(16)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(project(":utils"))
    api(project(":logging"))
    api(project(":api-logansquare"))
    api(project(":navigation"))

    api("androidx.multidex:multidex:2.0.1")
    api("net.danlew:android.joda:2.9.9.4")

    implementation("androidx.appcompat:appcompat:${versions["appcompat"]}")
    implementation("com.crashlytics.sdk.android:crashlytics:${versions["crashlytics"]}@aar") {
        isTransitive = true
    }
}