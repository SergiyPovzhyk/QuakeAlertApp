package com.example.quakealertapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.quakealertapp.MainFragmentViewModel
import com.example.quakealertapp.R
import com.example.quakealertapp.adapter.QuakeAlertAdapter
import com.example.quakealertapp.api.AlertQuakeService
import com.example.quakealertapp.constVal.BASE_URL
import com.example.quakealertapp.model.QuakeDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment :Fragment() {
    private lateinit var viewModel:MainFragmentViewModel
    private var recyclerView:RecyclerView? = null
    private var quakeAlertAdapter:QuakeAlertAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]


        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        observeUpdates()
    }


    private fun initUI() {
        recyclerView = view?.findViewById(R.id.recyclerView)
        quakeAlertAdapter = QuakeAlertAdapter()
        recyclerView?.adapter = quakeAlertAdapter
    }
    private fun observeUpdates() {
        viewModel.properties.observe(viewLifecycleOwner, Observer {
            val dataList = it
            quakeAlertAdapter?.setQuakeAlert(dataList)
            quakeAlertAdapter?.notifyDataSetChanged()
        })
    }

}