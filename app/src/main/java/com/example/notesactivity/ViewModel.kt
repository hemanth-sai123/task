package com.example.notesactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.RoomDataBase
import com.example.notesactivity.models.Notes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(application: Application):AndroidViewModel(application) {

    val repository:Repository
    init {

        repository = Repository()
        repository.initRoomDataBase(application)

    }
    fun addNotes(notes: Notes){
        repository.addNotes(notes)
    }

}