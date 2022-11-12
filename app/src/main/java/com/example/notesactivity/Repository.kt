package com.example.notesactivity

import android.app.Application
import com.example.jetpackcompose.NotesDao
import com.example.jetpackcompose.RoomDataBase
import com.example.notesactivity.models.Notes

class Repository() {

    lateinit var dao:NotesDao
    fun initRoomDataBase(application:Application){
        dao = RoomDataBase.getDatabase(application).notesDao()
    }

    fun addNotes(notes: Notes) {

        dao.addNote(notes)
    }

}