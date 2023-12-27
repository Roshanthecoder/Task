package com.yeslab.task.network

import com.yeslab.task.model.MovCharacterList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("demos/marvel")
    suspend fun getMovieList(): Response<ArrayList<MovCharacterList>>
}