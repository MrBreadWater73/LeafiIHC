// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false

    // Añadir el plugin de Google Services
    id("com.google.gms.google-services") version "4.4.2" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Asegúrate de que el classpath para google-services esté disponible
        classpath("com.google.gms:google-services:4.4.2")
    }
}

