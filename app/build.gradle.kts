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
        versionCode = 68
        versionName = "0.5.10.1"

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
    val files = listOf(
        "strings.xml",
        "strings_bank.xml",
        "strings_categories.xml",
        "strings_cities.xml",
        "strings_goods.xml"
    )

    inputs.files(files.map { file("src/main/res/values/$it") })
    outputs.files(files.map { file("src/main/res/values-en/$it") })

    doLast {
        files.forEach { name ->
            val src = file("src/main/res/values/$name")
            val dest = file("src/main/res/values-en/$name")

            if (src.exists()) {
                dest.parentFile.mkdirs()
                dest.writeText(src.readText())
                println("Copied values/$name → values-en/$name")
            }
        }
    }
}

tasks.named("preBuild").configure {
    dependsOn("copyEnglishStrings")
}