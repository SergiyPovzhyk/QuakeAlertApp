package com.example.quakealertapp.model

data class QuakeDto(
    val features: List<Feature>,
    val type: String
)

data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)

data class Geometry(
    val coordinates: List<Double>,
    val type: String
)

data class Properties(
    val depth: Double,
    val locality: String,
    val magnitude: Double,
    val mmi: Int,
    val publicID: String,
    val quality: String,
    val time: String
)