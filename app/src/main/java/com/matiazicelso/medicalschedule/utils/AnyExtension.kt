package com.matiazicelso.medicalschedule.utils

import java.lang.IllegalStateException

fun Any.readJson(
    file: String
) = (this::class.java.classLoader ?: throw IllegalStateException("Failed to get the classLoader"))
    .getResourceAsStream("json/$file.json")
    .bufferedReader()
    .use { it.readText() }