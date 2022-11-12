package com.example.notesactivity.di

import retrofit2.http.GET

interface RetroInstanceService {

    @GET("")
    suspend fun fetch()
}