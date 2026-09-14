package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.AdmissionAlertRepository
import com.example.data.repository.AlertUrgency

@Composable
fun CountdownBadge(
    deadlineDateStr: String,
    testDateStr: String,
    modifier: Modifier = Modifier
) {
    val daysRemaining = AdmissionAlertRepository.calculateDaysRemaining(deadlineDateStr)
    val testDaysRemaining = AdmissionAlertRepository.calculateDaysRemaining(testDateStr)
    val urgency = AdmissionAlertRepository.getUrgencyStatus(deadlineDateStr, testDateStr)

    val (bgColor, textColor, text) = when (urgency) {
        AlertUrgency.DEADLINE_CLOSING_SOON -> Triple(
            Color(0xFFFEE2E2),
            Color(0xFFB91C1C),
            "⚠️ $daysRemaining Days to Apply!"
        )
        AlertUrgency.REGISTRATION_OPEN -> Triple(
            Color(0xFFD1FAE5),
            Color(0xFF065F46),
            "🟢 Apply by $deadlineDateStr ($daysRemaining d left)"
        )
        AlertUrgency.TEST_IMMINENT -> Triple(
            Color(0xFFFEF3C7),
            Color(0xFF92400E),
            "⚡ Test in $testDaysRemaining Days"
        )
        AlertUrgency.UPCOMING_TEST -> Triple(
            Color(0xFFDBEAFE),
            Color(0xFF1E40AF),
            "📅 Test: $testDateStr"
        )
        AlertUrgency.COMPLETED -> Triple(
            Color(0xFFF1F5F9),
            Color(0xFF64748B),
            "Concluded"
        )
    }

    Box(
        modifier = modifier
            .background(color = bgColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
