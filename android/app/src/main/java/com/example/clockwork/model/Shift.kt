package com.example.clockwork.model

import java.util.Date

class Shift(
    val startTime: Date,
    val endTime: Date,
    val breaks: MutableList<Break> = mutableListOf(),
    val position: String
    // TODO: add member stuff
) {

}