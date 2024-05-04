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
    val lifecycle_version = "2.6.2"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")
    implementation("androidx.compose.runtime:runtime-rxjava3:1.5.2")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
    implementation("org.jboss.resteasy:resteasy-client:6.2.8.Final")
    //implementation("org.eclipse.microprofile.config:microprofile-config-api:3.1") // for application.properties config loader
    implementation("io.smallrye.config:smallrye-config:3.4.4")

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