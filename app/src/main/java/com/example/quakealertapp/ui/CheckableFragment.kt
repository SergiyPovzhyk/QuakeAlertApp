package com.example.quakealertapp.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.quakealertapp.R

class CheckableFragment : Fragment() {
    var checkableDate:TextView? =null
    var checkableLocality:TextView? = null
    var checkableMagnitude:TextView? = null
    var checkableIntensity:TextView? = null
    var intensity:Any? = null
    var magnitude:Any? = null
    var locality:Any? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkable, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        workWithData()
    }

    private fun workWithData() {
        val inputData = arguments
        magnitude = inputData?.getString("magnitude").toString()
        locality = inputData?.getString("locality").toString()
        intensity = inputData?.getString("text_magnitude").toString()

        val date = inputData?.getString("date").toString()
        checkableLocality?.text = locality.toString()
        checkableMagnitude?.text = magnitude.toString()
        checkableDate?.text = date
        checkableIntensity?.text = intensity.toString()
    }

    private fun initUi() {
        checkableLocality = view?.findViewById(R.id.checkableDistance)
        checkableMagnitude = view?.findViewById(R.id.checkableMagnitude)
        checkableDate = view?.findViewById(R.id.checkableDate)
        checkableIntensity = view?.findViewById(R.id.checkableIntensity)
    }
}