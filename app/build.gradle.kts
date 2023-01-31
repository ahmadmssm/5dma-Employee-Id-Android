plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("com.google.devtools.ksp") version "1.8.0-1.0.9"
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
    id("de.jensklingenberg.ktorfit") version "1.0.0"
}

android {

    namespace = "com.ams.khdmaEmployeeId"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ams.khdmaEmployeeId"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDefault = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        resources.excludes.addAll(listOf(
            "META-INF/DEPENDENCIES",
            "META-INF/LICENSE",
            "META-INF/LICENSE.txt",
            "META-INF/license.txt",
            "META-INF/NOTICE",
            "META-INF/NOTICE.txt",
            "META-INF/notice.txt",
            "META-INF/ASL2.0",
            "META-INF/project.properties",
            "META-INF/MANIFEST.MF"))
    }

    // For KSP
    applicationVariants.configureEach {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/${this@configureEach.name}/kotlin")
            }
        }
    }

    flavorDimensions.add("default")

    productFlavors {
        create("development") {
            dimension = "default"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            // https://stackoverflow.com/a/50888237/6927433
            manifestPlaceholders["appLabel"] = "@string/app_name_dev"
            buildConfigField("String", "BASE_URL", "\"https://restcountries.com/v2/\"")
//            buildConfigField(BuildSystem.buildConfig.booleanField, BuildSystem.buildConfig.isProduction, "false")
//            firebaseAppDistribution {
//                releaseNotesFile = "firebaseAppDistribution/releaseNotes/debugReleaseNotes.txt"
//                testersFile = "firebaseAppDistribution/testers.txt"
//                groupsFile = "firebaseAppDistribution/testingGroups.txt"
//            }
        }
        create("uat") {
            dimension = "default"
            applicationIdSuffix = ".uat"
            versionNameSuffix = "-UAT"
            // https://stackoverflow.com/a/50888237/6927433
            manifestPlaceholders["appLabel"] = "@string/app_name_uat"
            isDefault = true
            buildConfigField("String", "BASE_URL", "\"https://restcountries.com/v2/\"")
//            buildConfigField(BuildSystem.buildConfig.booleanField, BuildSystem.buildConfig.isProduction, "false")
//            firebaseAppDistribution {
//                releaseNotesFile = "firebaseAppDistribution/releaseNotes/debugReleaseNotes.txt"
//                testersFile = "firebaseAppDistribution/testers.txt"
//                groupsFile = "firebaseAppDistribution/testingGroups.txt"
//            }
        }
        create("production") {
            dimension = "default"
            manifestPlaceholders["appLabel"] = "@string/app_name"
            buildConfigField("String", "BASE_URL", "\"https://restcountries.com/v2/\"")
//            buildConfigField(BuildSystem.buildConfig.booleanField, BuildSystem.buildConfig.isProduction, "true")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //
    implementation("io.ktor:ktor-client-core:2.2.2")
    implementation("io.ktor:ktor-client-okhttp:2.2.2")
    implementation("io.ktor:ktor-client-content-negotiation:2.2.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.2")
    implementation("io.ktor:ktor-client-content-negotiation:2.2.2")
    // Kotlinx-Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    //
    ksp("de.jensklingenberg.ktorfit:ktorfit-ksp:1.0.0-beta17")
    implementation("de.jensklingenberg.ktorfit:ktorfit-lib:1.0.0-beta17")
    /*
     * Added this library to solve this issue with Android API 32 and above :
     * java.lang.IllegalArgumentException: com.teavaro.funnelConnectSDK.androidSample.debug:
     * Targeting S+ (version 31 and above) requires that one of FLAG_IMMUTABLE or FLAG_MUTABLE
     * be specified when creating a PendingIntent.
     * Strongly consider using FLAG_IMMUTABLE, only use FLAG_MUTABLE if some functionality
     * depends on the PendingIntent being mutable, e.g. if it needs to be used with inline replies or bubbles.
     * Ref: https://stackoverflow.com/a/70870040/6927433
     */
    implementation("androidx.work:work-runtime-ktx:2.7.1")

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.github.chuckerteam.chucker:library:3.5.2")
    // implementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")
    //
    // implementation("com.github.kenglxn.QRGen:android:3.0.1")
    implementation("com.google.zxing:core:3.4.1")
    //
    // Koin for Android
    implementation("io.insert-koin:koin-android:3.3.2")
    implementation("io.insert-koin:koin-annotations:1.1.0")
    ksp("io.insert-koin:koin-ksp-compiler:1.1.0")
    //
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
}