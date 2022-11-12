package com.example.jetpackcompose

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.notesactivity.models.Notes
import kotlinx.coroutines.flow.Flow
//var r="reward_table"

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
    fun getNotes(): List<Notes>


    @Insert(onConflict =IGNORE)
    fun addNote(notes: Notes)

}
