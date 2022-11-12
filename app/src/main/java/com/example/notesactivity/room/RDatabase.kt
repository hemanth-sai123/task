package com.example.jetpackcompose

import android.content.Context
import androidx.room.*

import com.example.notesactivity.models.Notes


@Database(entities = [ Notes::class], version = 1, exportSchema = false)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
    companion object{
        @Volatile
        private var INSTANCE:RoomDataBase?=null

        fun getDatabase(context: Context):RoomDataBase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBase::class.java,"database"
                ).allowMainThreadQueries().build()
                INSTANCE =instance
                return instance
            }
        }
    }
}