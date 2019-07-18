package eu.antoniolopez.playground.core.extension

import java.util.*

fun timestampInMs(): Long = now().timeInMillis

fun now(): Calendar = today()

fun today(): Calendar = GregorianCalendar.getInstance(Locale.getDefault())
