package com.example.quakealertapp.api

import com.example.quakealertapp.constVal.GET_QUAKE
import com.example.quakealertapp.model.Properties
import com.example.quakealertapp.model.QuakeDto
import retrofit2.Call
import retrofit2.http.GET

interface AlertQuakeService {

    @GET(GET_QUAKE)

    fun getAlertQuake():Call<QuakeDto>

}