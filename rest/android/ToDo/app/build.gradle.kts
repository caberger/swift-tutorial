plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "at.htl.leonding"
    compileSdk = 34

    defaultConfig {
        applicationId = "at.htl.leonding"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/DEPENDENCIES.txt"
        }
    }
}

dependencies {
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.ui.tooling.preview)
    implementation(libs.compose.material)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform(libs.test.compose.bom))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation(libs.debug.compose.ui.tooling)
    debugImplementation(libs.debug.compose.manifest)

    implementation(libs.rxjava.rxjava)
    implementation(libs.compose.rxjava)
    implementation(libs.rxjava.android)

    implementation(libs.dagger.hilt)
    kapt(libs.kapt.hilt)
    implementation(libs.fasterxml.jackson)

    implementation(libs.resteasy.client)
    implementation(libs.smallrye.config)
}
kapt {
    correctErrorTypes = true
}

/** JavaDoc helper.
 * This tasks writes the the class-path to a file that can be used with javadoc
 * javadoc ... @javadoc.txt
 */
tasks.register("javadoc-params") {
    doLast {
        val variant = project.android.applicationVariants
        val release = variant.filter{ it.buildType.name == "release" }.first()
        val outputFile = project.layout.buildDirectory.file("javadoc.txt").get().asFile
        outputFile.printWriter().use { out ->
            val classpath = release.compileConfiguration.joinToString(separator=":") { it.toString() }
            out.println("--class-path " + classpath)
            out.println()
        }
        println("javadoc options written to " + outputFile.absolutePath)
    }
}