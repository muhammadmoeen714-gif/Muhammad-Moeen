package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admission_alerts")
data class AdmissionAlertEntity(
    @PrimaryKey val id: String,
    val universityName: String,
    val shortName: String,
    val testName: String,
    val category: String, // "Engineering", "Medical", "Computing", "Law", "Business", "General"
    val registrationDeadline: String, // "2026-07-15"
    val testDate: String, // "2026-07-25"
    val meritListDate: String, // "2026-08-10"
    val city: String,
    val feeInPkr: Int,
    val eligibility: String,
    val aggregateFormulaDescription: String,
    val officialUrl: String,
    val isAlertEnabled: Boolean = false,
    val reminderDaysBefore: Int = 3,
    val userNotes: String = ""
)

@Entity(tableName = "bookmarked_lectures")
data class BookmarkedLectureEntity(
    @PrimaryKey val lectureId: String,
    val savedAtTimestamp: Long = System.currentTimeMillis()
)
