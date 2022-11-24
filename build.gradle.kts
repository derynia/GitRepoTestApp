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
        classpath(BuildPlugins.gradle)
        classpath(BuildPlugins.ktlint)
    }
}

allprojects {
    apply(plugin = BuildPlugins.ktlintMain)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
