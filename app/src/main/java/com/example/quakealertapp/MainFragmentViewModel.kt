package com.example.quakealertapp

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quakealertapp.api.AlertQuakeService
import com.example.quakealertapp.constVal.BASE_URL
import com.example.quakealertapp.model.Feature
import com.example.quakealertapp.model.Properties
import com.example.quakealertapp.model.QuakeDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragmentViewModel :ViewModel() {

    private var _properties = MutableLiveData<List<Feature>>()
    val properties:LiveData<List<Feature>> = _properties

    init {
        buildRetrofit()
    }

    private fun buildRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AlertQuakeService::class.java)

        service.getAlertQuake().enqueue(object : Callback<QuakeDto> {
            override fun onResponse(call: Call<QuakeDto>, response: Response<QuakeDto>) {
                val body = response.body()?.features
                _properties.value = body
            }

            override fun onFailure(call: Call<QuakeDto>, t: Throwable) {
                println(t.message)
            }

        })
    }
}