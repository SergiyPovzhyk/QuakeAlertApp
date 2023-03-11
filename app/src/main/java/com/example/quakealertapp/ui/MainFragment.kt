package com.example.quakealertapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.quakealertapp.MainFragmentViewModel
import com.example.quakealertapp.R
import com.example.quakealertapp.adapter.QuakeAlertAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment :Fragment() {
    private lateinit var viewModel:MainFragmentViewModel
    private var recyclerView:RecyclerView? = null
    private var quakeAlertAdapter:QuakeAlertAdapter? = null
    private var bundle = Bundle()
    private var fragment = CheckableFragment()

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
        //quakeAlertAdapter = QuakeAlertAdapter()
        quakeAlertAdapter = QuakeAlertAdapter(){
            sendDataToFragment(it)
        }
        recyclerView?.adapter = quakeAlertAdapter
    }

    private fun sendDataToFragment(it: ArrayList<String?>) {
        bundle.putString("date",it[0])
        bundle.putString("locality",it[1])
        bundle.putString("magnitude",it[2])
        bundle.putString("text_magnitude",it[3])

        fragment.arguments = bundle

        parentFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()

    }

    private fun observeUpdates() {
        viewModel.properties.observe(viewLifecycleOwner, Observer {
            val dataList = it
            quakeAlertAdapter?.setQuakeAlert(dataList)
            quakeAlertAdapter?.notifyDataSetChanged()
        })
    }

}