// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp) apply false
}

ktlint {
    enableExperimentalRules.set(true)
}

dependencies {
    ktlintRuleset(libs.compose.rules.ktlint)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
