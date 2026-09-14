package com.example.data.repository

import com.example.data.local.AdmissionAlertDao
import com.example.data.local.AdmissionAlertEntity
import com.example.data.seed.CatalogData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class AdmissionAlertRepository(
    private val alertDao: AdmissionAlertDao
) {
    val allAlerts: Flow<List<AdmissionAlertEntity>> = alertDao.getAllAlerts()
    val subscribedAlerts: Flow<List<AdmissionAlertEntity>> = alertDao.getSubscribedAlerts()

    suspend fun initializeIfEmpty() {
        val currentList = alertDao.getAllAlerts().first()
        if (currentList.isEmpty()) {
            alertDao.insertAll(CatalogData.INITIAL_ADMISSION_ALERTS)
        }
    }

    suspend fun toggleAlert(alertId: String, enable: Boolean, reminderDays: Int = 3) {
        alertDao.toggleAlertSubscription(alertId, enable, reminderDays)
    }

    suspend fun updateNotes(alertId: String, notes: String) {
        alertDao.updateUserNotes(alertId, notes)
    }

    suspend fun getAlertById(alertId: String): AdmissionAlertEntity? {
        return alertDao.getAlertById(alertId)
    }

    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        fun calculateDaysRemaining(targetDateStr: String): Long {
            return try {
                val targetDate = dateFormat.parse(targetDateStr) ?: return 0
                val today = Date()
                val diffInMillis = targetDate.time - today.time
                val days = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
                if (days < 0) 0 else days
            } catch (e: Exception) {
                0
            }
        }

        fun getUrgencyStatus(deadlineDateStr: String, testDateStr: String): AlertUrgency {
            val deadlineDays = calculateDaysRemaining(deadlineDateStr)
            val testDays = calculateDaysRemaining(testDateStr)

            return when {
                deadlineDays in 1..3 -> AlertUrgency.DEADLINE_CLOSING_SOON
                deadlineDays > 3 -> AlertUrgency.REGISTRATION_OPEN
                testDays in 1..7 -> AlertUrgency.TEST_IMMINENT
                testDays > 7 -> AlertUrgency.UPCOMING_TEST
                else -> AlertUrgency.COMPLETED
            }
        }
    }
}

enum class AlertUrgency(val label: String, val badgeColorHex: Long) {
    DEADLINE_CLOSING_SOON("Deadline Closing Soon", 0xFFDC2626),
    REGISTRATION_OPEN("Registration Open", 0xFF047857),
    TEST_IMMINENT("Test Approaching", 0xFFD97706),
    UPCOMING_TEST("Upcoming", 0xFF2563EB),
    COMPLETED("Test Concluded", 0xFF64748B)
}
