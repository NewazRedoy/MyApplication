package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getNotesSortedByTitleAsc(): Flow<List<Note>>

    @Query("SELECT * FROM notes ORDER BY title DESC")
    fun getNotesSortedByTitleDesc(): Flow<List<Note>>

    @Query("SELECT * FROM notes ORDER BY timestamp ASC")
    fun getNotesSortedByDateAsc(): Flow<List<Note>>

    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getNotesSortedByDateDesc(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
    @Query("""
        SELECT * FROM notes 
        WHERE title LIKE '%' || :searchQuery || '%' 
        OR content LIKE '%' || :searchQuery || '%'
        ORDER BY 
        CASE 
            WHEN :sortType = 'TITLE_ASC' THEN title END ASC,
            CASE 
            WHEN :sortType = 'TITLE_DESC' THEN title END DESC,
            CASE 
            WHEN :sortType = 'DATE_ASC' THEN timestamp END ASC,
            CASE 
            WHEN :sortType = 'DATE_DESC' THEN timestamp END DESC
    """)
    fun searchNotes(searchQuery: String, sortType: String): Flow<List<Note>>
}