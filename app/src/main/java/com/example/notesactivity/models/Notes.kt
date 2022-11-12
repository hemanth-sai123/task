package com.example.notesactivity.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id:Int?=null,
    @ColumnInfo(name="note_name")
    val note_name:String?=null,
)