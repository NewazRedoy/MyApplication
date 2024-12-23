package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    fun getNotesSorted(sortType: SortType): Flow<List<Note>> {
        return when (sortType) {
            SortType.TITLE_ASC -> noteDao.getNotesSortedByTitleAsc()
            SortType.TITLE_DESC -> noteDao.getNotesSortedByTitleDesc()
            SortType.DATE_ASC -> noteDao.getNotesSortedByDateAsc()
            SortType.DATE_DESC -> noteDao.getNotesSortedByDateDesc()
        }
    }

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    fun searchNotes(query: String, sortType: SortType): Flow<List<Note>> {
        return noteDao.searchNotes(query, sortType.name)
    }
}