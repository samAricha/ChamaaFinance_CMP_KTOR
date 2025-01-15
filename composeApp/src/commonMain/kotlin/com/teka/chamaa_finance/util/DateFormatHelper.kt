package com.teka.chamaa_finance.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

expect object DateFormatHelper {
    fun getFormattedDate(
        iso8601Timestamp: Long,
        format: String
    ): String
}

fun getCurrentTimeAsLong(): Long {
    val currentInstant: Instant = Clock.System.now()
    return currentInstant.toEpochMilliseconds()
}

fun Long?.selectedDateMillisToLocalDateTime(): LocalDateTime {
    return Instant.fromEpochMilliseconds(this ?: 0)
        .toLocalDateTime(TimeZone.currentSystemDefault())
}

fun today(): LocalDateTime {
    return Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
}


fun LocalTime.formattedTimeBasedOnTimeFormat(timeFormat: Int): String {
    return if (timeFormat == 12) {
        val hourTo12HourSystem = if (this.hour > 12) {
            this.hour - 12
        } else {
            this.hour
        }
        "$hourTo12HourSystem:${
            this.minute.formattedZeroMinutes()
        } ${if (this.hour > 12) "PM" else "AM"}"
    } else {
        "${this.hour}:${this.minute.formattedZeroMinutes()}"
    }
}

fun Int.formattedZeroMinutes(): String {
    return if (this < 10) {
        "0$this"
    } else {
        this.toString()
    }
}