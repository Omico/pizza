/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.gradle.project

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import java.util.Calendar
import java.util.TimeZone

internal fun Project.configureCommonAndroid(
    domain: String,
    compileSdk: Int,
    minSdk: Int,
    namespace: String = "$domain.${name.replace("-", ".")}",
    coreLibraryDesugaringVersion: String = "2.0.4",
) {
    extensions.configure<CommonExtension<*, *, *, *, *, *>>("android") {
        this.namespace = namespace
        this.compileSdk = compileSdk
        defaultConfig {
            this.minSdk = minSdk
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
            isCoreLibraryDesugaringEnabled = true
        }
    }
    dependencies.add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:$coreLibraryDesugaringVersion")
}

internal fun generateVersionCode(year: Int, month: Int, date: Int): Int {
    val calendar = Calendar.getInstance().apply {
        timeZone = TimeZone.getTimeZone("UTC")
        set(year, month, date, 0, 0, 0)
    }
    return System.currentTimeMillis().minus(calendar.timeInMillis).div(1000).toInt()
}
