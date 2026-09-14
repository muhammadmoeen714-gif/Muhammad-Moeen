package com.example.data.model

data class Teacher(
    val id: String,
    val name: String,
    val subject: String,
    val experience: String,
    val institution: String,
    val rating: Float,
    val totalStudents: String,
    val bio: String
)

data class PracticeQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class LectureChapter(
    val timestampSeconds: Int,
    val title: String
)

data class VideoLecture(
    val id: String,
    val title: String,
    val subject: String, // "Physics", "Chemistry", "Mathematics", "Biology", "English", "Analytical & Intelligence"
    val testFocus: String, // "MDCAT", "ECAT / NET", "FAST / GIKI", "All Tests"
    val teacherName: String,
    val teacherTitle: String,
    val duration: String,
    val durationSeconds: Int,
    val views: String,
    val rating: Float,
    val thumbnailUrl: String,
    val videoUrl: String,
    val highYieldSummary: String,
    val formulasAndTricks: List<String>,
    val chapters: List<LectureChapter>,
    val practiceQuestions: List<PracticeQuestion>
)
