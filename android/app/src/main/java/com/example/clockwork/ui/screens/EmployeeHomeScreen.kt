package com.example.clockwork.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clockwork.ui.components.ClockInDashboard
import com.example.clockwork.ui.components.Greeting
import com.example.clockwork.viewmodel.HomeViewModel

@Composable
fun EmployeeHomeScreen(viewModel: HomeViewModel) {
    val hasUpcomingShifts by viewModel.hasUpcomingShifts.collectAsState()
    val shiftStartTime by viewModel.shiftStartTime.collectAsState()
    val shiftEndTime by viewModel.shiftEndTime.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Greeting(name = viewModel.memberName)
        Spacer(modifier = Modifier.height(32.dp))
        if (hasUpcomingShifts && shiftStartTime != null && shiftEndTime != null) {
            ClockInDashboard(true, shiftStartTime!!, shiftEndTime!!, 10.0f, emptyList(), "Technician")
        }
    }
}

