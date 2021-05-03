import com.google.protobuf.gradle.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.protobuf")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.example.home_hackathon2"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    sourceSets {
        getByName("main").java.srcDir("src/main/kotlin")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta05"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    implementation("androidx.compose.ui:ui:1.0.0-beta05")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta05")
    implementation("androidx.compose.foundation:foundation:1.0.0-beta05")
    implementation("androidx.compose.material:material:1.0.0-beta05")
    implementation("androidx.activity:activity-compose:1.3.0-alpha07")

    runtimeOnly("io.grpc:grpc-okhttp:1.37.0")
    implementation("io.grpc:grpc-protobuf-lite:1.37.0")
    implementation("io.grpc:grpc-kotlin-stub:1.0.0") {
        exclude("com.google.protobuf", "protobuf-java")
    }
    implementation("com.google.protobuf:protobuf-javalite:3.15.8")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

protobuf {
    protoc { artifact = "com.google.protobuf:protoc:3.15.8" }
    plugins {
        id("java") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.37.0"
        }
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.37.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.0.0:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("java") {
                    option("lite")
                }
                id("grpc") {
                    option("lite")
                }
                id("grpckt") {
                    option("lite")
                }
            }
        }
    }
}
