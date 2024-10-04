package com.example.clockwork.model

import java.util.Date

enum class ClockEventType {
    StartShift,
    BreakStart,
    BackToWork,
    EndShift
}

class ClockEvent(
    val type: ClockEventType,
    val time: Date,
    val breaksTaken: Boolean = true
) {

}