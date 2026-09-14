package com.example.data.repository

import com.example.data.local.BookmarkedLectureDao
import com.example.data.local.BookmarkedLectureEntity
import com.example.data.model.Teacher
import com.example.data.model.VideoLecture
import com.example.data.seed.CatalogData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LectureRepository(
    private val bookmarkDao: BookmarkedLectureDao
) {
    val allLectures: List<VideoLecture> = CatalogData.VIDEO_LECTURES
    val allTeachers: List<Teacher> = CatalogData.TEACHERS

    val bookmarkedLectureIds: Flow<Set<String>> = bookmarkDao.getAllBookmarks().map { list ->
        list.map { it.lectureId }.toSet()
    }

    fun isBookmarked(lectureId: String): Flow<Boolean> {
        return bookmarkDao.isLectureBookmarked(lectureId)
    }

    suspend fun toggleBookmark(lectureId: String, currentBookmarked: Boolean) {
        if (currentBookmarked) {
            bookmarkDao.removeBookmark(lectureId)
        } else {
            bookmarkDao.bookmarkLecture(BookmarkedLectureEntity(lectureId = lectureId))
        }
    }

    fun getLectureById(id: String): VideoLecture? {
        return allLectures.find { it.id == id }
    }

    fun getTeacherById(id: String): Teacher? {
        return allTeachers.find { it.id == id }
    }

    fun getLecturesBySubject(subject: String): List<VideoLecture> {
        if (subject.equals("All", ignoreCase = true)) return allLectures
        return allLectures.filter { it.subject.equals(subject, ignoreCase = true) }
    }
}
