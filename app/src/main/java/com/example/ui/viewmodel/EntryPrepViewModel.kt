package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AdmissionAlertEntity
import com.example.data.local.AppDatabase
import com.example.data.model.Teacher
import com.example.data.model.VideoLecture
import com.example.data.repository.AdmissionAlertRepository
import com.example.data.repository.LectureRepository
import com.example.ui.components.NotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AggregateFormulaPreset(
    val title: String,
    val description: String,
    val testWeight: Float,
    val fscWeight: Float,
    val matricWeight: Float,
    val testTotalDefault: Int
) {
    MDCAT(
        title = "National MDCAT (Medical)",
        description = "50% Entry Test + 40% FSc Pre-Medical + 10% Matric",
        testWeight = 0.50f,
        fscWeight = 0.40f,
        matricWeight = 0.10f,
        testTotalDefault = 200
    ),
    NUST_NET(
        title = "NUST NET (Engineering & CS)",
        description = "75% NET + 15% FSc/HSSC + 10% Matric/SSC",
        testWeight = 0.75f,
        fscWeight = 0.15f,
        matricWeight = 0.10f,
        testTotalDefault = 200
    ),
    UET_ECAT(
        title = "UET ECAT (Engineering)",
        description = "33% ECAT + 50% FSc Pre-Engineering + 17% Matric",
        testWeight = 0.33f,
        fscWeight = 0.50f,
        matricWeight = 0.17f,
        testTotalDefault = 400
    ),
    FAST_NU(
        title = "FAST-NUCES (Computing)",
        description = "50% Entry Test + 50% Intermediate (FSc/ICS)",
        testWeight = 0.50f,
        fscWeight = 0.50f,
        matricWeight = 0.0f,
        testTotalDefault = 100
    ),
    GIKI(
        title = "GIKI Admission Test",
        description = "85% GIKI Written Test + 15% Intermediate",
        testWeight = 0.85f,
        fscWeight = 0.15f,
        matricWeight = 0.0f,
        testTotalDefault = 200
    ),
    COMSATS_NAT(
        title = "COMSATS (NTS NAT)",
        description = "50% NTS NAT + 40% Intermediate + 10% Matric",
        testWeight = 0.50f,
        fscWeight = 0.40f,
        matricWeight = 0.10f,
        testTotalDefault = 100
    )
}

data class AggregateCalcState(
    val selectedPreset: AggregateFormulaPreset = AggregateFormulaPreset.MDCAT,
    val matricObtained: String = "1020",
    val matricTotal: String = "1100",
    val fscObtained: String = "475",
    val fscTotal: String = "550", // Part 1 or Part 2
    val testObtained: String = "168",
    val testTotal: String = "200",
    val calculatedAggregate: Double? = null
)

class EntryPrepViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val alertRepository = AdmissionAlertRepository(database.admissionAlertDao())
    private val lectureRepository = LectureRepository(database.bookmarkedLectureDao())

    // Alerts state
    val allAlerts: StateFlow<List<AdmissionAlertEntity>> = alertRepository.allAlerts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val subscribedAlerts: StateFlow<List<AdmissionAlertEntity>> = alertRepository.subscribedAlerts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _searchQueryAlerts = MutableStateFlow("")
    val searchQueryAlerts: StateFlow<String> = _searchQueryAlerts.asStateFlow()

    val filteredAlerts: StateFlow<List<AdmissionAlertEntity>> = combine(
        allAlerts,
        _selectedCategory,
        _searchQueryAlerts
    ) { alerts, category, query ->
        alerts.filter { alert ->
            val matchesCategory = (category == "All" || alert.category.equals(category, ignoreCase = true))
            val matchesQuery = query.isBlank() ||
                    alert.universityName.contains(query, ignoreCase = true) ||
                    alert.shortName.contains(query, ignoreCase = true) ||
                    alert.testName.contains(query, ignoreCase = true) ||
                    alert.city.contains(query, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Lectures state
    val allLectures: List<VideoLecture> = lectureRepository.allLectures
    val allTeachers: List<Teacher> = lectureRepository.allTeachers

    val bookmarkedIds: StateFlow<Set<String>> = lectureRepository.bookmarkedLectureIds
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    private val _selectedSubject = MutableStateFlow("All")
    val selectedSubject: StateFlow<String> = _selectedSubject.asStateFlow()

    private val _searchQueryLectures = MutableStateFlow("")
    val searchQueryLectures: StateFlow<String> = _searchQueryLectures.asStateFlow()

    val filteredLectures: StateFlow<List<VideoLecture>> = combine(
        _selectedSubject,
        _searchQueryLectures
    ) { subject, query ->
        allLectures.filter { lecture ->
            val matchesSubject = (subject == "All" || lecture.subject.equals(subject, ignoreCase = true))
            val matchesQuery = query.isBlank() ||
                    lecture.title.contains(query, ignoreCase = true) ||
                    lecture.teacherName.contains(query, ignoreCase = true) ||
                    lecture.testFocus.contains(query, ignoreCase = true)
            matchesSubject && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), allLectures)

    // Current lecture player state
    private val _currentPlayingLecture = MutableStateFlow<VideoLecture?>(allLectures.firstOrNull())
    val currentPlayingLecture: StateFlow<VideoLecture?> = _currentPlayingLecture.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _playbackProgress = MutableStateFlow(0f)
    val playbackProgress: StateFlow<Float> = _playbackProgress.asStateFlow()

    private val _playbackSpeed = MutableStateFlow("1.0x")
    val playbackSpeed: StateFlow<String> = _playbackSpeed.asStateFlow()

    // Solved MCQs in player
    private val _userQuizAnswers = MutableStateFlow<Map<String, Int>>(emptyMap())
    val userQuizAnswers: StateFlow<Map<String, Int>> = _userQuizAnswers.asStateFlow()

    // Aggregate Calculator State
    private val _calcState = MutableStateFlow(AggregateCalcState())
    val calcState: StateFlow<AggregateCalcState> = _calcState.asStateFlow()

    // Notification toast / message feedback
    private val _notificationMessage = MutableStateFlow<String?>(null)
    val notificationMessage: StateFlow<String?> = _notificationMessage.asStateFlow()

    init {
        viewModelScope.launch {
            alertRepository.initializeIfEmpty()
            // Calculate initial aggregate
            calculateAggregate()
        }
    }

    fun setCategoryFilter(category: String) {
        _selectedCategory.value = category
    }

    fun setSearchQueryAlerts(query: String) {
        _searchQueryAlerts.value = query
    }

    fun setSubjectFilter(subject: String) {
        _selectedSubject.value = subject
    }

    fun setSearchQueryLectures(query: String) {
        _searchQueryLectures.value = query
    }

    fun toggleAlertSubscription(alert: AdmissionAlertEntity, context: Application) {
        viewModelScope.launch {
            val newStatus = !alert.isAlertEnabled
            alertRepository.toggleAlert(alert.id, newStatus, alert.reminderDaysBefore)

            if (newStatus) {
                val sent = NotificationHelper.sendTestAlertNotification(
                    context = context,
                    notificationId = alert.id.hashCode(),
                    universityName = alert.shortName,
                    testName = alert.testName,
                    deadlineOrDate = "Deadline: ${alert.registrationDeadline}",
                    message = "Automatic reminder scheduled for ${alert.reminderDaysBefore} days before test"
                )
                _notificationMessage.value = if (sent) {
                    "🔔 Alert set for ${alert.shortName}! Real-time reminder enabled."
                } else {
                    "🔔 Alert scheduled for ${alert.shortName} in-app (enable notification permission for push alerts)."
                }
            } else {
                _notificationMessage.value = "Alert disabled for ${alert.shortName}."
            }
        }
    }

    fun updateReminderDays(alertId: String, days: Int) {
        viewModelScope.launch {
            alertRepository.toggleAlert(alertId, true, days)
            _notificationMessage.value = "Reminder updated: $days days before test date."
        }
    }

    fun updateAlertNotes(alertId: String, notes: String) {
        viewModelScope.launch {
            alertRepository.updateNotes(alertId, notes)
        }
    }

    fun toggleLectureBookmark(lectureId: String, isCurrentlyBookmarked: Boolean) {
        viewModelScope.launch {
            lectureRepository.toggleBookmark(lectureId, isCurrentlyBookmarked)
            _notificationMessage.value = if (!isCurrentlyBookmarked) {
                "Lecture saved to your study library!"
            } else {
                "Lecture removed from saved."
            }
        }
    }

    fun selectLectureForPlayer(lecture: VideoLecture) {
        _currentPlayingLecture.value = lecture
        _isPlaying.value = true
        _playbackProgress.value = 0.05f
        _userQuizAnswers.value = emptyMap()
    }

    fun togglePlayPause() {
        _isPlaying.value = !_isPlaying.value
    }

    fun seekToProgress(progress: Float) {
        _playbackProgress.value = progress.coerceIn(0f, 1f)
    }

    fun setPlaybackSpeed(speed: String) {
        _playbackSpeed.value = speed
    }

    fun answerQuizQuestion(questionId: String, optionIndex: Int) {
        _userQuizAnswers.value = _userQuizAnswers.value.toMutableMap().apply {
            put(questionId, optionIndex)
        }
    }

    fun clearNotificationMessage() {
        _notificationMessage.value = null
    }

    // Aggregate Calculator Methods
    fun setCalcPreset(preset: AggregateFormulaPreset) {
        _calcState.value = _calcState.value.copy(
            selectedPreset = preset,
            testTotal = preset.testTotalDefault.toString()
        )
        calculateAggregate()
    }

    fun updateCalcField(matricObt: String? = null, matricTot: String? = null,
                        fscObt: String? = null, fscTot: String? = null,
                        testObt: String? = null, testTot: String? = null) {
        _calcState.value = _calcState.value.copy(
            matricObtained = matricObt ?: _calcState.value.matricObtained,
            matricTotal = matricTot ?: _calcState.value.matricTotal,
            fscObtained = fscObt ?: _calcState.value.fscObtained,
            fscTotal = fscTot ?: _calcState.value.fscTotal,
            testObtained = testObt ?: _calcState.value.testObtained,
            testTotal = testTot ?: _calcState.value.testTotal
        )
        calculateAggregate()
    }

    private fun calculateAggregate() {
        try {
            val state = _calcState.value
            val preset = state.selectedPreset

            val mObt = state.matricObtained.toDoubleOrNull() ?: 0.0
            val mTot = state.matricTotal.toDoubleOrNull() ?: 1100.0
            val fObt = state.fscObtained.toDoubleOrNull() ?: 0.0
            val fTot = state.fscTotal.toDoubleOrNull() ?: 1100.0
            val tObt = state.testObtained.toDoubleOrNull() ?: 0.0
            val tTot = state.testTotal.toDoubleOrNull() ?: preset.testTotalDefault.toDouble()

            val mPct = if (mTot > 0) (mObt / mTot) * 100.0 else 0.0
            val fPct = if (fTot > 0) (fObt / fTot) * 100.0 else 0.0
            val tPct = if (tTot > 0) (tObt / tTot) * 100.0 else 0.0

            val agg = (tPct * preset.testWeight) + (fPct * preset.fscWeight) + (mPct * preset.matricWeight)
            _calcState.value = state.copy(calculatedAggregate = String.format(java.util.Locale.US, "%.2f", agg).toDoubleOrNull())
        } catch (e: Exception) {
            _calcState.value = _calcState.value.copy(calculatedAggregate = null)
        }
    }
}
