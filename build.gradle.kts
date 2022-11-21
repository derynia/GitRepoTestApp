buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugins.android)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.hilt)
        classpath("com.android.tools.build:gradle:7.3.1")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}