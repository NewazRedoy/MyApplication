package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.NoteDatabase
import com.example.myapplication.data.NoteRepository

class NoteApplication : Application() {
    private val database by lazy { NoteDatabase.getDatabase(this) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}