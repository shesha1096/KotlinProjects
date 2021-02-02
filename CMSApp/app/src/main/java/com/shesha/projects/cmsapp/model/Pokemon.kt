package com.shesha.projects.cmsapp.model

data class Pokemon(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
) {
    data class Result(
        val name: String,
        val url: String
    )
}