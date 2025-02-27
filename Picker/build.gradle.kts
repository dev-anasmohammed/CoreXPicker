import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
//    id("module.publication")
    id("com.vanniktech.maven.publish") version "0.28.0"
}

android {
    namespace = "com.dev.anasmohammed.corex.picker"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "11"
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

mavenPublishing {
    coordinates(
        groupId = "io.github.dev-anasmohammed",
        artifactId = "CoreXPicker",
        version = "1.0.1"
    )

    pom {
        name.set("CoreXPicker")
        description.set(
            "This library to handle picking media (Photos , Videos and Audio) file and Non Media files (Documents and another types ) from storage without permission.\n" +
                    "Only Camera picker need Camera permission and record audio permission if need you need to record video.\n"
        )
        inceptionYear.set("2025")
        url.set("https://github.com/dev-anasmohammed/CoreXPicker")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licences/MIT")
            }
        }

        developers {
            developer {
                id.set("")
                name.set("")
            }
        }

        scm {
            url.set("https://github.com/dev-anasmohammed/CoreXPicker")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}