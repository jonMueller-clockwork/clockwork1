package com.example.clockwork.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clockwork.model.Break
import com.example.clockwork.model.Member
import com.example.clockwork.model.Shift
import com.example.clockwork.utils.DateUtils.Companion.shortMonthDay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel(
    val member: Member,
    private val shifts: List<Shift>
) : ViewModel() {
    private val _upcomingShifts = MutableStateFlow(shifts)
    private val _shiftStartTime = MutableStateFlow(shifts.firstOrNull()?.startTime)
    private val _shiftEndTime = MutableStateFlow(shifts.firstOrNull()?.endTime)
    private val _currentTime = MutableStateFlow(Date())
    private val _shortMonthDay = MutableStateFlow(shortMonthDay())
    private val _breaks = MutableStateFlow(shifts.firstOrNull()?.breaks)
    private var _breakNumber: Int = 0

    val upcomingShifts: StateFlow<List<Shift>> = _upcomingShifts
    val shiftStartTime: StateFlow<Date?> = _shiftStartTime
    val shiftEndTime: StateFlow<Date?> = _shiftEndTime
    val currentTime: StateFlow<Date> = _currentTime
    val shortMonthDay: StateFlow<String> = _shortMonthDay
    val breaks: StateFlow<List<Break>?> = _breaks

    val memberName: String
        get() = member.name

    val hasUpcomingShifts: StateFlow<Boolean> = _upcomingShifts.map { it.isNotEmpty() }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    init {
        // Update current time every minute to update timeline
        updateCurrentTime()
    }

    private fun updateCurrentTime() {
        viewModelScope.launch {
            while (true) {
                _currentTime.value = Date()
                _shortMonthDay.value = shortMonthDay()
                delay(60000L) // Wait for 1 minute
            }
        }
    }

    fun startShift() {
        _shiftStartTime.value = Date()
    }

    fun endShift() {
        _shiftEndTime.value = Date()
    }

    fun breakStart() {
        if (_breakNumber >= (_breaks.value?.size ?: 0)) {
            // new break (not created by manager)
            val newBreak = Break()
            _breaks.value?.add(newBreak)
        } else {
            // configured break
            _breaks.value?.get(_breakNumber)?.breakStart()
        }

        _breaks.value = _breaks.value
    }

    fun backToWork() {
        _breaks.value?.get(_breakNumber)?.backToWork()
        _breaks.value = _breaks.value

        _breakNumber += 1
    }

}