package com.example.clockwork.model

import java.util.Date

class Break(
    val scheduledStartTime: Date? = null,
    val scheduledEndTime: Date? = null,
    var startTime: Date? = null,
    var endTime: Date? = null
) {
    constructor() : this(startTime = Date())

    fun isActive(currentTime: Date = Date()): Boolean {
        return startTime != null && endTime == null
    }

    fun breakStart() {
        startTime = Date()
    }

    fun backToWork() {
        endTime = Date()
    }

    fun displayStartTime(): Date {
        startTime?.let { return it }
        scheduledStartTime?.let { return it }

        return Date()
    }

    fun displayEndTime(): Date {
        endTime?.let { return it }
        if (startTime == null) {
            scheduledEndTime?.let { return it }
        }

        return Date()
    }
}
