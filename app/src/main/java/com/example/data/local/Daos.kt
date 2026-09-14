package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AdmissionAlertDao {
    @Query("SELECT * FROM admission_alerts ORDER BY testDate ASC")
    fun getAllAlerts(): Flow<List<AdmissionAlertEntity>>

    @Query("SELECT * FROM admission_alerts WHERE isAlertEnabled = 1 ORDER BY testDate ASC")
    fun getSubscribedAlerts(): Flow<List<AdmissionAlertEntity>>

    @Query("SELECT * FROM admission_alerts WHERE id = :id LIMIT 1")
    suspend fun getAlertById(id: String): AdmissionAlertEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(alerts: List<AdmissionAlertEntity>)

    @Update
    suspend fun update(alert: AdmissionAlertEntity)

    @Query("UPDATE admission_alerts SET isAlertEnabled = :enabled, reminderDaysBefore = :days WHERE id = :id")
    suspend fun toggleAlertSubscription(id: String, enabled: Boolean, days: Int)

    @Query("UPDATE admission_alerts SET userNotes = :notes WHERE id = :id")
    suspend fun updateUserNotes(id: String, notes: String)
}

@Dao
interface BookmarkedLectureDao {
    @Query("SELECT * FROM bookmarked_lectures")
    fun getAllBookmarks(): Flow<List<BookmarkedLectureEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_lectures WHERE lectureId = :lectureId)")
    fun isLectureBookmarked(lectureId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkLecture(bookmark: BookmarkedLectureEntity)

    @Query("DELETE FROM bookmarked_lectures WHERE lectureId = :lectureId")
    suspend fun removeBookmark(lectureId: String)
}
