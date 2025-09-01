plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.scipath.becomeaking"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.scipath.becomeaking"
        minSdk = 26
        targetSdk = 35
        versionCode = 29
        versionName = "0.4.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    dataBinding {
        enable = true
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.gson)
    implementation(libs.mockito)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.register("copyEnglishStrings") {
    val defaultStrings = file("src/main/res/values/strings.xml")
    val enStrings = file("src/main/res/values-en/strings.xml")

    inputs.file(defaultStrings)
    outputs.file(enStrings)

    doLast {
        if (defaultStrings.exists()) {
            enStrings.parentFile.mkdirs()
            enStrings.writeText(defaultStrings.readText())
            println("Copied values/strings.xml → values-en/strings.xml")
        }
    }
}

tasks.named("preBuild").configure {
    dependsOn("copyEnglishStrings")
}