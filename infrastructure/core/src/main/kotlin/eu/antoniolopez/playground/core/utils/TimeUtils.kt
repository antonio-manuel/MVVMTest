package eu.antoniolopez.playground.core.utils

import java.util.*

class TimeUtils {
    private fun now(): Calendar = today()

    private fun today(): Calendar = GregorianCalendar.getInstance(Locale.getDefault())

    fun timestampInMs(): Long = now().timeInMillis

    fun isTimeOlderThanNow(timestamp: Long?, maxDifferenceInMs: Long): Boolean =
        timestamp?.let {
            (timestampInMs() - timestamp) > maxDifferenceInMs
        } ?: true
}
