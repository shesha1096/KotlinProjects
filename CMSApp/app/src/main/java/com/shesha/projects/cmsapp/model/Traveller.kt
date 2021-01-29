package com.shesha.projects.cmsapp.model

data class Traveller(
    val `data`: List<Data>,
    val totalPages: Int,
    val totalPassengers: Int
) {
    data class Data(
        val __v: Int,
        val _id: String,
        val airline: Any,
        val name: String,
        val trips: Int
    )
}